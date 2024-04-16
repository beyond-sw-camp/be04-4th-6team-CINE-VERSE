<template>
    <div>
      <h1>퀴즈</h1>
      <div v-for="quiz in quizzes" :key="quiz.quizId">
        <p>{{ quiz.quizQuestion }}</p>
        <input type="text" v-model="answers[quiz.quizId]">
        <button @click="submitAnswer(quiz.quizId)">제출</button>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        quizzes: [],
        answers: {}
      };
    },
    mounted() {
      this.fetchQuizzes();
    },
    methods: {
      async fetchQuizzes() {
        try {
          const response = await axios.get('/event_board/quizzes');
          this.quizzes = response.data;
        } catch (error) {
          console.error('Error fetching quizzes:', error);
        }
      },
      async submitAnswer(quizId) {
        const answer = this.answers[quizId];
        try {
          const response = await axios.post('/event_board/quiz/submit', {
            quizId: quizId,
            answer: answer
          });
          console.log('Answer submitted successfully:', response.data);
          // 서버로부터 응답을 받아 정답률이 변경되었다면 다시 데이터를 불러올 수 있음
          this.fetchQuizzes();
        } catch (error) {
          console.error('Error submitting answer:', error);
        }
      }
    }
  };
  </script>