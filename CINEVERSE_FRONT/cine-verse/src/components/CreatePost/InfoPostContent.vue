<template>
  <div>
    <h2>게시물 작성</h2>
    <form @submit.prevent="submitPost">
      <label for="infoTitle">제목:</label>
      <input type="text" id="infoTitle" v-model="infoTitle" required>
      
      <label for="infoContent">내용:</label>
      <textarea id="infoContent" v-model="infoContent" required></textarea>

      <label for="images">사진 업로드:</label>
      <input type="file" id="images" multiple accept="image/*" @change="handleFileChange">

      <button type="submit">게시물 작성</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/mainRouter';

const infoTitle = ref('');
const infoContent = ref('');
const images = ref([]);


// 게시물 작성 함수
const submitPost = async () => {
  try {
    // 쿠키에서 사용자 아이디 가져오기
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
      infoCategory: { infoCategoryId: 1, infoCategory: "야호" }
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
