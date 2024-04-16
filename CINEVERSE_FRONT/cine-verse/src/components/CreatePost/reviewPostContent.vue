<template>
    <div class="post-form">
      <h2>게시물 작성</h2>
      <form @submit.prevent="submitPost" class="form">
        <div class="form-group">
          <label for="reviewTitle">제목:</label>
          <input type="text" id="reviewTitle" v-model="reviewTitle" required>
        </div>
        
        <div class="form-group">
          <label for="reviewContent">내용:</label>
          <textarea id="reviewContent" v-model="reviewContent" required></textarea>
        </div>
  
        <div class="form-group">
          <label for="reviewCategory">카테고리:</label>
          <select id="reviewCategory" v-model="reviewCategory">
            <option value="1">영화</option>
            <option value="2">영화관</option>
            <option value="3">음식</option>
            <option value="4">기타</option>
          </select>
        </div>
  
        <div class="form-group">
          <label for="images">사진 업로드:</label>
          <input type="file" id="images" multiple accept="image/*" @change="handleFileChange">
        </div>
  
        <button type="submit" class="submit-btn">게시물 작성</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  import router from '@/router/mainRouter';
  
  const reviewTitle = ref('');
  const reviewContent = ref('');
  const reviewCategory = ref('1'); // 기본 카테고리 설정
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
      formData.append('reviewBoard', JSON.stringify({
        reviewTitle: reviewTitle.value,
        reviewContent: reviewContent.value,
        member: { memberId: memberId },
        reviewCategory: { reviewCategoryId: reviewCategory.value, reviewCategory: "카테고리 " + reviewCategory.value }
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
      await axios.post('http://localhost:8081/review_board/regist', formData);
  
      console.log('게시물 작성 완료');
      router.push('/review_board/list');
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
@import url('@/assets/css/CreatePost/reviewPostContent.css');
  </style>
  