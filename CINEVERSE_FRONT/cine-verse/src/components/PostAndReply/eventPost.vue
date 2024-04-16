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
        <h2 class="boardtitle">{{ event.eventTitle }}</h2>
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
      <div v-if="event.quiz" class="quiz-container">
        <h2>{{ event.quiz.quizQuestion }}</h2>
        <input type="text" v-model="answer" placeholder="정답을 입력하세요">
        <button @click="submitAnswer">제출</button>
      </div>
    </div>
    <Like />
    <div class="like">
      <button type="button" @click="deletePost">
        삭제
      </button>
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
const answer = ref('');
const quizResult = ref(null);

const fetchEvent = () => {
  axios.get(`http://localhost:8081/event_board/${eventId.params.eventId}`)
    .then(response => {
      event.value = response.data;
    })
    .catch(error => {
      console.error("Error fetching event:", error);
    });
}

onBeforeMount(() => {
  fetchEvent();
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

// 서버에 퀴즈 답안을 제출하는 함수
const submitAnswer = async () => {
  try {
    const response = await axios.post('http://localhost:8081/event_board/quiz/submit', {
      quizId: event.value.quiz.quizId,
      quizAnswer: answer.value
    });
    quizResult.value = response.data; // 서버로부터 받은 퀴즈 정답률을 저장
  } catch (error) {
    console.error('퀴즈 답안을 제출하는 중 에러 발생:', error);
  }
};
</script>

<style scoped>
@import url('@/assets/css/postAndReply/eventPost.css');
</style>