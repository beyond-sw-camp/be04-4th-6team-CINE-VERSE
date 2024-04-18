<template>
    <hr class="replyregistline">
    <div class="replys">
      <div class="allreply" v-for="reply in replys" :key="reply.infoCommentId" >
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
            <button type="button" class="closebutton" @click="removeReply(reply.infoCommentId)">
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

    return decodeURIComponent(nicknameValue);
  } catch (error) {
    console.error('사용자 닉네임 추출 중 에러 발생:', error);
    return null;
  }
};

const nickname = ref(extractNicknameFromCookie());

onMounted(() => {
  nickname.value = extractNicknameFromCookie();
});

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

    const infoId = router.currentRoute.value.params.infoId;

    const response = await axios.post('http://localhost:8081/info_comment/regist', {
      commentContent: newComment.value,
      member: { memberId },
      infoId:  infoId
    });

    replys.value.push(response.data);
    newComment.value = '';
  } catch (error) {
    console.error('댓글 작성 중 에러 발생:', error);
  }
};

const removeReply = async (infoCommentId) => {
  try {
    if (confirm('댓글을 삭제하시겠습니까?')) {
      await axios.patch(`http://localhost:8081/info_comment/delete/${infoCommentId}`,{
        infoCommentId: infoCommentId
      });
      
      replys.value = replys.value.filter(reply => reply.replyId !== infoCommentId);
    }
  } catch (error) {
    console.error('댓글 삭제 중 에러 발생:', error);
  }
};

onMounted(async () => {
  try { 
    const infoId = router.currentRoute.value.params.infoId;
    
    const response = await axios.get(`http://localhost:8081/info_comment/list?infoId=${infoId}`);
    replys.value = response.data.filter(comment => {
  const commentInfoId = parseInt(comment.infoId);
  const routerInfoId = parseInt(infoId);
  return commentInfoId === routerInfoId;
});
console.log('서버로부터 받은 정보:', replys.value);
  } catch (error) {
    console.error('데이터 가져오는 중 에러 발생:', error);
  }
});
</script>

<style scoped>
  @import url('@/assets/css/PostAndReply/Reply.css');
</style>