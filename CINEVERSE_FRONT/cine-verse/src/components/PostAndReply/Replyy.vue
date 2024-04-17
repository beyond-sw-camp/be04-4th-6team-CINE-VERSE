<template>
    <hr class="replyregistline">
    <div class="replys">
      <div class="allreply" v-for="reply in replys" :key="reply.commentId">
        <div class="replywriterdiv">
          <span v-if="reply.member">{{ reply.member.nickname }}</span>
        </div>
        <div class="replycontentdiv">
          <p>{{ reply.commentContent }}</p>
        </div>
        <div class="replydatediv">
          <p>작성일: {{ reply.commentDate }}</p>
        </div>
        <div class="closebuttondiv">
          <form action="" name="deleteReply" method="post">
            <button type="button" class="closebutton" @click="removeReply(reply.commentId)">
              <img src="@/assets/img/delete3.png" style="width: 15px; height: 15px;">
            </button>
          </form>
        </div>
      </div>
      <div class="noreply" v-if="!replys.length">
        <p>댓글이 없습니다.</p>
      </div>
      <div class="registreplywriterdiv">
        <span>{{ nickname }}</span>
      </div>
      <div class="registreplydiv">
        <form id="comment" @submit.prevent="submitReply">
          <div class="commentbox">
            <textarea type="text" class="box" id="reply" v-model="newComment" placeholder="타인의 권리를 침해하거나 명예를 훼손하는 댓글은 운영원칙 및 관련 법률에 의거, 제재를 받을 수 있습니다." required></textarea>
          </div>
          <input type="submit" class="btn" value="등록">
        </form>
      </div>
    </div>
  </template>
  
  
<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const replys = ref([]);
const newComment = ref('');


// 사용자 닉네임을 쿠키에서 추출하는 함수
const extractNicknameFromCookie = () => {
  try {
    const nicknameCookie = document.cookie.split('; ').find(cookie => cookie.startsWith('nickname='));
    if (!nicknameCookie) {
      console.error('사용자 닉네임 정보가 없습니다.');
      return null;
    }

    const nicknameValue = nicknameCookie.split('=')[1];
    if (!nicknameValue) {
      console.error('사용자 닉네임이 없습니다.');
      return null;
    }

    // URL 디코딩 후 반환
    return decodeURIComponent(nicknameValue);
  } catch (error) {
    console.error('사용자 닉네임 추출 중 에러 발생:', error);
    return null;
  }
};

// 사용자 닉네임을 추출하여 변수에 할당
const nickname = ref(extractNicknameFromCookie());

// 페이지가 로드될 때마다 사용자 닉네임을 업데이트
onMounted(() => {
  nickname.value = extractNicknameFromCookie();
});
// console.log(replys)
const submitReply = async () => {
  try {
    const memberIdCookie = document.cookie.split('; ').find(cookie => cookie.startsWith('memberId='));
    if (!memberIdCookie) {
      console.error('사용자 정보가 없습니다.');
      return;
    }

    const memberId = memberIdCookie.split('=')[1];
    if (!memberId) {
      console.error('사용자 아이디가 없습니다.');
      return;
    }

    // Vue Router를 통해 reviewId 가져오기
    const reviewId = router.currentRoute.value.params.reviewId;

    const response = await axios.post('http://localhost:8081/review_comment/regist', {
      commentContent: newComment.value,
      member: { memberId },
      reviewId:  reviewId  // Vue Router를 통해 가져온 reviewId 사용
    });

    // 새로운 댓글을 추가하고 입력 필드 초기화
    replys.value.push(response.data);
    newComment.value = '';
  } catch (error) {
    console.error('댓글 작성 중 에러 발생:', error);
  }
};

const removeReply = async (commentId) => {
  try {
    if (confirm('댓글을 삭제하시겠습니까?')) {
      await axios.patch(`http://localhost:8081/review_comment/delete/${commentId}`,{
        commentId: commentId
      });
      
      replys.value = replys.value.filter(reply => reply.commentId !== commentId);
    }
  } catch (error) {
    console.error('댓글 삭제 중 에러 발생:', error);
  }
};
onMounted(async () => {
  try {
    // Vue Router를 통해 reviewId 가져오기
    const reviewId = router.currentRoute.value.params.reviewId;
    
    const response = await axios.get(`http://localhost:8081/review_comment/list?reviewId=${reviewId}`);
    replys.value = response.data.filter(comment => {
  const commentreviewId = parseInt(comment.reviewId);
  const routerreviewId = parseInt(reviewId);
  return commentreviewId === routerreviewId;
});
console.log('서버로부터 받은 정보:', replys.value);
  } catch (error) {
    console.error('데이터 가져오는 중 에러 발생:', error);
  }
});
</script>

<style scoped>
@import url('@/assets/css/postAndReply/Reply.css');
</style>