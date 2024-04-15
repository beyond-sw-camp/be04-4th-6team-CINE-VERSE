<template>
    <div>
      <div class="allreply" v-for="reply in replys" :key="reply.infoCommentId" >
        <div class="replywriterdiv">
          <span v-if="reply.member">{{ reply.member.memberName }}</span>
        </div>
        <div class="replycontentdiv">
          <p>{{ reply.commentContent }}</p>
        </div>
        <div class="replydatediv">
          <p>{{ reply.commentDate }}</p>
        </div>
        <div class="closebuttondiv">
          <form action="" name="deleteReply" method="post">
            <button type="button" class="closebutton" @click="removeReply(reply.infoCommentId)">
              <img src="@/assets/img/delete.png" style="width: 5px; height: 5px;">
            </button>
          </form>
        </div>
      </div>
      <div class="noreply" v-if="!replys.length">
        <p>댓글이 없습니다.</p>
      </div>
  
      <hr class="replyregistline">
      <div class="registreplywriterdiv">
        <span v-if="replys.length > 0 && replys[0].member">{{ replys[0].member.memberName }}</span>
      </div>
      <div class="registreplydiv">
        <form id="comment" @submit.prevent="submitReply">
          <div class="commentbox">
            <textarea type="text" class="box" id="reply" v-model="newComment" placeholder="타인의 권리를 침해하거나 명예를 훼손하는 댓글은 운영원칙 및 관련 법률에 의거, 제재를 받을 수 있습니다." required></textarea>
          </div>
          <input type="submit" class="btn" value="등록">
        </form>
      </div>
      <hr class="replyregistbottomline">
    </div>
  </template>
  
  
<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const replys = ref([]);
const newComment = ref('');

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

    // Vue Router를 통해 infoId 가져오기
    const infoId = router.currentRoute.value.params.infoId;

    const response = await axios.post('http://localhost:8081/info_comment/regist', {
      commentContent: newComment.value,
      member: { memberId },
      infoId:  infoId  // Vue Router를 통해 가져온 infoId 사용
    });

    // 새로운 댓글을 추가하고 입력 필드 초기화
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
    // Vue Router를 통해 infoId 가져오기
    const infoId = router.currentRoute.value.params.infoId;
    
    const response = await axios.get(`http://localhost:8081/info_comment/list?infoId=${infoId}`);
    replys.value = response.data.filter(comment => {
  const commentInfoId = parseInt(comment.infoId);
  const routerInfoId = parseInt(infoId);
  return commentInfoId === routerInfoId;
});
    // replys.value = response.data;
  } catch (error) {
    console.error('데이터 가져오는 중 에러 발생:', error);
  }
});
</script>

<style scoped>
    .box {
            width:100%;
            height:100px;
        }
    
    .closebutton{
            border:0px;
            cursor: pointer;
            
        }

    .btn {
            background-color: #e9740e;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            font-style: bold;
            float: right;

        }
        .replyline {
            width:95%;
            margin-left: 2%;
            margin-right: 2%;
            height: 2px;
            border: 0;
            background-color: #e9740e;
        }

        .replyregistline {
            width:95%;
            margin-top: 20%;
            margin-left: 2%;
            margin-right: 2%;
            height: 2px;
            border: 0;
            background-color: #e9740e;
        }

        .replyregistbottomline {
            width:95%;
            margin-top: 3%;
            margin-left: 2%;
            margin-right: 2%;
            height: 2px;
            border: 0;
            background-color: #e9740e;
        }

        .replytitlediv {
            margin-top: 10%;
            padding-left: 10px;
            padding-right: 10px;
            margin-left: 2%;
            float: left;
        }

        .replywriterdiv {
            padding-left: 10px;
            padding-right: 10px;
            margin-left: 3%;
            width: 10%;
        }

        .replycontentdiv {
            padding-left: 10px;
            padding-right: 10px;
            margin-left: 10%;
            width: 20%;
        }

        .replydatediv {
            padding-left: 10px;
            padding-right: 10px;
            margin-left: 30%;
            width: 15%;
        }

        .closebuttondiv {
            padding-left: 10px;
            padding-right: 10px;
            margin-left: 2%;
            
        }

        .allreply {
            display: flex;
            flex-direction: rows;
            justify-content: flex-start;
            width: 100%;
            font-size:12px;
        }

        .noreply {
            width: 100%;
            font-size:12px;
            text-align: center;
        }

        .registreplywriterdiv{
            margin-top:2%;
            padding-left: 10px;
            padding-right: 10px;
            margin-left: 2%;
            float: left;
        }

        .registreplydiv {
            margin-top: 1%;
            margin-left: 5%;
            float: left;
            width: 82%;
        }

        .replytitle {
            font-size: 20px;
            margin-top: 20px;
        }

        textarea::placeholder {
            font-size:12px;
            padding-left: 1%;
            padding-top: 1%;
        }
</style>