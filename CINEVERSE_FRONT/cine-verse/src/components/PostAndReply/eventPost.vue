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
        <!-- <h3 class="writer">{{ event.member.nickname }}</h3> -->
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
      <div v-if="quizRate !== null">
  <h4>퀴즈 정답률: {{ quizRate }}%</h4>
</div>

<!-- 아래에 추가 -->
<div v-else>
  <h2>퀴즈 결과가 없습니다.</h2>
</div>
      <button type="button" @click="deletePost">
        삭제
      </button>
      <div v-if="submitError" class="error-message">
        <p>이미 퀴즈 답안을 제출했습니다.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from '@/router/mainRouter';
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

const event = ref({});
const eventId = useRoute();
const answer = ref('');
const quizRate = ref(0);


onMounted(() => {
  fetchEvent();
  fetchQuizResult();
});

const submitError = ref(false);
const fetchEvent = async () => {
  try {
    const memberIdCookie = document.cookie.split('; ').find(cookie => cookie.startsWith('memberId='));
    if (!memberIdCookie) {
      console.error('사용자 정보가 없습니다.');
      return;
    }

    const memberId = Number(memberIdCookie.split('=')[1]);
    if (!memberId) {
      console.error('사용자 아이디가 없습니다.');
      return;
    }
    console.log('쿠키로부터 받은 멤버 아이디:', memberId);

    axios.get(`http://localhost:8081/event_board/${eventId.params.eventId}`)
      .then(response => {
        event.value = response.data;
      })
      .catch(error => {
        console.error("Error fetching event:", error);
      });
  } catch (error) {
    console.error("Error fetching event:", error);
  }
};

const fetchQuizResult = async () => {
  try {
    const response = await axios.get('http://localhost:8081/event_board/correct_rate');
    quizRate.value = response.data[0].correctRate;
    
    console.log('퀴즈 결과:', response.data[0].correctRate);
  } catch (error) {
    console.error('퀴즈 결과를 가져오는 중 에러 발생:', error);
  }
};

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

const submitAnswer = async () => {
  try {
    const memberIdCookie = document.cookie.split('; ').find(cookie => cookie.startsWith('memberId='));
    if (!memberIdCookie) {
      console.error('사용자 정보가 없습니다.');
      return;
    }

    const memberId = Number(memberIdCookie.split('=')[1]);
    if (!memberId) {
      console.error('사용자 아이디가 없습니다.');
      return;
    }
    console.log('쿠키로부터 받은 멤버 아이디:', memberId);

    const isCorrect = event.value.quiz.quizAnswer === answer.value;

    const response = await axios.post('http://localhost:8081/event_board/quiz/submit', {
      quizId: event.value.quiz.quizId,
      memberId: memberId,
      quizCorrect: isCorrect ? 'Y' : 'N'
    });
    submitError.value = false;
  } catch (error) {
    console.error('퀴즈 답안을 제출하는 중 에러 발생:', error);
    submitError.value = true;
  }
};
</script>

<style scoped>
@import url('@/assets/css/postAndReply/eventPost.css');
</style>