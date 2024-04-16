<template>
    <div class="post-form">
      <h2>게시물 작성</h2>
      <form @submit.prevent="submitPost" class="form">
        <div class="form-group">
          <label for="eventTitle">제목:</label>
          <input type="text" id="eventTitle" v-model="eventTitle" required>
        </div>
        <div class="form-group">
          <label for="eventContent">내용:</label>
          <textarea id="eventContent" v-model="eventContent" required></textarea>
        </div>
        <div class="form-group">
          <label for="quizQuestion">퀴즈 질문:</label>
          <input type="text" id="quizQuestion" v-model="quizQuestion">
        </div>
        <div class="form-group">
          <label for="quizAnswer">퀴즈 정답:</label>
          <input type="text" id="quizAnswer" v-model="quizAnswer">
        </div>
        <button type="submit" class="submit-btn">게시물 작성</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  import router from '@/router/mainRouter';
  
  const eventTitle = ref('');
  const eventContent = ref('');
  const quizQuestion = ref('');
  const quizAnswer = ref('');
  const memberId = ref(null); // 사용자 ID를 저장할 변수
  
  // 페이지가 로드될 때 사용자 ID를 가져오는 함수
  const fetchMemberId = () => {
    const memberIdCookie = document.cookie.split('; ')
      .find(cookie => cookie.startsWith('memberId='));
    if (memberIdCookie) {
      memberId.value = memberIdCookie.split('=')[1];
    }
  };
  
  // 게시물 작성 함수
  const submitPost = async () => {
    if (!memberId.value) {
      console.error('사용자 ID가 없습니다.');
      return;
    }
  
    try {
      const postData = {
        eventTitle: eventTitle.value,
        eventContent: eventContent.value,
        member: { memberId: memberId.value },
        quiz: { quizQuestion: quizQuestion.value, quizAnswer: quizAnswer.value } // 퀴즈 정보를 포함시킴
      };
      // 게시물 등록 요청 보내기
      await axios.post('http://localhost:8081/event_board/regist', postData);
  
      console.log('게시물 작성 완료');
      router.push('/event_board/list');
    } catch (error) {
      console.error('게시물 작성 오류:', error);
    }
  };
  
  // 페이지 로드 시 사용자 ID 가져오기
  fetchMemberId();
  </script>