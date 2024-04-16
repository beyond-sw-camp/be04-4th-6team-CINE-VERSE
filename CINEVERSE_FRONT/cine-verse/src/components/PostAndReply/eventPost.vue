<style scoped>
@import url('@/assets/css/postAndReply/eventPost.css');
</style> 

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
    </div>
  <div>
    <!-- 퀴즈를 표시할 영역 -->
    <div v-if="quiz">
      <h2>{{ quiz.quizQuestion }}</h2>
      <input type="text" v-model="answer" placeholder="정답을 입력하세요">
      <button @click="submitAnswer">제출</button>
    </div>
    <!-- 퀴즈 정답률을 표시할 영역 -->
    <div v-if="quizResult">
      <h2>퀴즈 정답률: {{ quizResult.correctRate }}%</h2>
    </div>
  </div>
</template>

<script setup>
import router from '@/router/mainRouter';
import Like from './Like.vue';
import axios from "axios";
import { ref, onBeforeMount } from "vue";
import { useRoute } from "vue-router";

const event = ref({});
const eventId = useRoute();
const quiz = ref(null);
const answer = ref('');
const quizResult = ref(null);

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

// 서버로부터 퀴즈를 가져오는 함수
const fetchQuiz = async () => {
  try {
    const response = await axios.get('/event_board/quiz');
    quiz.value = response.data; // 서버로부터 받은 퀴즈를 저장
  } catch (error) {
    console.error('퀴즈를 가져오는 중 에러 발생:', error);
  }
};

// 서버에 퀴즈 답안을 제출하는 함수
const submitAnswer = async () => {
  try {
    const response = await axios.post('/event_board/quiz/submit', {
      quizId: quiz.value.quizId,
      quizAnswer: answer.value
    });
    quizResult.value = response.data; // 서버로부터 받은 퀴즈 정답률을 저장
  } catch (error) {
    console.error('퀴즈 답안을 제출하는 중 에러 발생:', error);
  }
};

// 페이지가 로드될 때 퀴즈를 가져오는 함수 호출
fetchQuiz();
</script>
<style scoped>
@import url('@/assets/css/postAndReply/eventPost.css');
</style> 