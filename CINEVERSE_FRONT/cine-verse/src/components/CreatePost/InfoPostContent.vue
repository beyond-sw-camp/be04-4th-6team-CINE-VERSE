<template>
  <div class="post-form">
    <h2>게시물 작성</h2>
    <form @submit.prevent="submitPost" class="form">
      <div class="form-group">
        <label for="infoTitle">제목:</label>
        <input type="text" id="infoTitle" v-model="infoTitle" required>
      </div>
      
      <div class="form-group">
        <label for="infoContent">내용:</label>
        <textarea id="infoContent" v-model="infoContent" required></textarea>
      </div>

      <div class="form-group">
        <label for="infoCategory">카테고리:</label>
        <select id="infoCategory" v-model="infoCategory">
          <option value="1">특전</option>
          <option value="2">행사</option>
          <option value="3">할인</option>
          <option value="4">기타</option>
        </select>
      </div>

      <div class="form-group">
        <label for="images">사진 업로드:</label>
        <input type="file" id="images" multiple accept="image/*" @change="handleFileChange">
      </div>

      <button type="submit" class="submit-btn" :disabled="submitting">게시물 작성</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/mainRouter';

const infoTitle = ref('');
const infoContent = ref('');
const infoCategory = ref('1'); 
const images = ref([]);
const submitting = ref(false); 


const submitPost = async () => {
  if (submitting.value) return; 
  submitting.value = true; 

  try {
    const memberIdCookie = document.cookie.split('; ')
      .find(cookie => cookie.startsWith('memberId='));
    
    if (!memberIdCookie) {
      console.error('사용자 정보가 없습니다.');
      return;
    }

    const memberId = memberIdCookie.split('=')[1];
    if (!memberId) {
      console.error('사용자 아이디가 없습니다.');
      return;
    }

    const formData = new FormData();
    formData.append('infoBoard', JSON.stringify({
      infoTitle: infoTitle.value,
      infoContent: infoContent.value,
      member: { memberId: memberId },
      infoCategory: { infoCategoryId: infoCategory.value, infoCategory: "카테고리 " + infoCategory.value }
    }));

    // 이미지 배열이 비어있지 않은 경우에만 FormData에 이미지를 추가
    if (images.value.length > 0) {
      images.value.forEach(image => {
        formData.append('images', image);
      });
    } else {
      // 이미지 배열이 비어있는 경우 빈 이미지를 추가
      formData.append('images', new Blob(), 'empty_image');
    }

    // 게시물 등록 요청 보내기
    await axios.post('http://localhost:8081/info_board/regist', formData);

    console.log('게시물 작성 완료');
    router.push('/info_board/list');
  } catch (error) {
    console.error('게시물 작성 오류:', error);
  }
};

// 파일 변경 처리 함수
const handleFileChange = (event) => {
  images.value = Array.from(event.target.files);
};
</script>

<style scoped>
@import url('@/assets/css/CreatePost/infoPostContent.css');
</style>
