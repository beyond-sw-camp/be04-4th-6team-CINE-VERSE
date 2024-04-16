<template>
  <div class="wrapper">
    <section>
      <div class="clickdiv" @click="mainBoard()">
        <h1 class="boardname">이벤트 게시판</h1>
      </div>
      <hr class="boardtitleLine">
    </section>
    <div class="allboard">
      <div class="boardtitlediv">
        <h2 class="boardtitle"> {{ event.eventTitle }}</h2>
      </div>
      <div class="datediv">
        <h3 class="date">{{ event.eventDate }}</h3>
      </div>
      <div class="like">
        <button type="button" @click="editPost">
          수정
        </button>
      </div>
      <div class="writerdiv">
        <h3 class="writer">{{ event.member.nickname }}</h3>
      </div>
      <div class="viewandlike">
        <h3 class="viewandlike">{{ event.eventViewCount }}</h3>
      </div>
    </div>
    <hr class="titleLine">
    <div class="maincontent">
      <p v-html="event.eventContent"></p>
      <div v-if="event.images && event.images.length > 0" class="image-container">
        <img v-for="(image, index) in event.images" :key="index" :src="image.accessUrl" :alt="'Image ' + (index + 1)"
          class="event-image">
      </div>
    </div>
    <Like />
    <div class="like">
      <button type="button" @click="deletePost">
        삭제
      </button>
    </div>
    <Reply />
  </div>
</template>

<script setup>
import router from '@/router/mainRouter';
import Reply from './Reply.vue';
import Like from './Like.vue';
import axios from "axios";
import { ref, onBeforeMount } from "vue";
import { useRoute } from "vue-router";

const event = ref({});
const eventId = useRoute();

const fetchevent = () => {
  axios.get(`http://localhost:8081/event_board/${eventId.params.eventId}`)
    .then(response => {
      event.value = response.data;
    })
    .catch(error => {
      console.error("Error fetching event:", error);
    });
}

onBeforeMount(() => {
  fetchevent();
});

function mainBoard() {
  router.push('/event_board/list');
}

function deletePost() {
  axios.patch(`http://localhost:8081/event_board/delete/${eventId.params.eventId}`)
    .then(response => {
      console.log('게시물이 성공적으로 삭제되었습니다.');
      router.push('/event_board/list');
    })
    .catch(error => {
      console.error("Error deleting post:", error);
    });
}

</script>

<style scoped>
@import url('@/assets/css/postAndReply/eventPost.css');
</style>

<!-- <template>
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
          const response = await axios.get('http://localhost:8081/event_board/${eventId.params.eventId}');
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
  </script> -->