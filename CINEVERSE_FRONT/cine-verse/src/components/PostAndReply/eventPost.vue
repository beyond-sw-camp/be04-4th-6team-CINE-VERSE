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
        <h3 class="date">작성일: {{ event.eventDate }}</h3>
      </div>
      <div class="writerdiv">
        <h3 class="writer">작성자: {{ event && event.member ? event.member.nickname : 'Unknown' }}</h3>
      </div>
      <div class="viewandlike">
        <h3 class="viewandlike">조회수: {{ event.eventViewCount }}</h3>
      </div>
      <div class="edit">
                <button type="button" @click="editPost" class="editbtn">
                수정
            </button>
            </div>
            <div class="delete">
            <button type="button" @click="deletePost" class="deletebtn">
                삭제
            </button>
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
      <div v-if="event.quiz && event.quiz.correctRate !== null">
  <h4>퀴즈 정답률: {{  event.quiz.correctRate  }}%</h4>
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


onMounted(() => {
  fetchEvent();

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
        console.log(response.data);
      })
      .catch(error => {
        console.error("Error fetching event:", error);
      });
  } catch (error) {
    console.error("Error fetching event:", error);
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
    if (isCorrect) {
    window.alert('정답입니다.');
  } else {
    window.alert('틀렸습니다.');
  }
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