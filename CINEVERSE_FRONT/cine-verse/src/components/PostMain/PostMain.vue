<template>
  <div>
    <table class="table">
      <tr class="top">
        <td class="post">
          <h1 class="boardTitle">정보 게시판</h1>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td class="thing">
          <button @click="goToWritePage" class="writebutton">게시글 작성</button>
        </td>
      </tr>
      <tr class="header2">
        <td>
          <select v-model="sortBy" @change="sortInfo">
            <option value="infoDate">작성일자</option>
            <option value="memberId">작성자</option>
          </select>
        </td>
        <td class="search" id="search-1">
                <div id="searchDropdown">
                    <select class="form-control" name="search_type" v-model="search_type"
                        style="height: 30px; font-size: 12px;">
                        <option value="titleContent">제목 내용</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="writerId">작성자</option>
                    </select>
                  </div>
                  <div>
                    <input type="text" id='searchText' class="form-control" placeholder="Search..."
                        v-model="search_condition" @keyup.enter="callData">
                </div>
                <div class="">
                    <button class="btn" id="searchBtn" type="button" @click="callData">검색</button>
                </div>
              </td>

      </tr>
      <tr class="header1">
        <td class="num">게시글 번호</td>
        <td>게시글 제목</td>
        <td>카테고리</td>
        <td>조회수</td>
        <td>좋아요</td>
        <td>작성자</td>
        <td>작성일자</td>
      </tr>
      <tbody>
        <tr v-for="(item, index) in info" :key="item.infoId" class="allpost" @click="changeRouter(item.infoId)">
          <td>{{ index + 1 }}</td>
          <td class="boardname">{{ item.infoTitle }}</td>
          <td>{{ item.infoCategoryId }}</td>
          <td>{{ item.infoViewCount }}</td>
          <td>{{ item.postLike }}</td>
          <td>{{ item.member.memberName }}</td>
          <td>{{ item.infoDate }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios";
import router from "@/router/mainRouter";

const info = ref([]);
const index = 1;
// const sortBy = ref('infoDate'); // 기본 정렬 기준은 작성일자로 설정
// const searchQuery = ref(''); // 검색어 상태 추가

const fetchInfo = () => {
  axios.get(`http://localhost:8081/info_board/list`)
    .then(response => {
      info.value = response.data;
    })
    .catch(error => {
      console.error("Error fetching info:", error);
    });
};

onMounted(() => {
  fetchInfo();
});

function changeRouter(infoId) {
  router.push(`/info_board/${infoId}`);
}

function goToWritePage(){
  router.push(`/info_board/regist`);
}

// function sortInfo() {
//   info.value.sort((a, b) => {
//     if (sortBy.value === 'infoDate') {
//       return new Date(b.infoDate) - new Date(a.infoDate);
//     } else if (sortBy.value === 'memberId') {
//       return a.memberId.localeCompare(b.memberId);
//     }
//   });
// }


</script>

<style scoped>

table {
  margin: auto;
  width: 68%;
  height: auto;
  min-height: 100%;
  border-radius: 5px;
  border-collapse: collapse;
  border-top: none;
}

.boardname {
  text-decoration: none;
  color: black;
  cursor: pointer;
}

.boardTitle {
  font-size: 32px;
  margin-top: 50px;
}

.titleLine {
  width: 440%;
  margin-left: 2%;
  margin-right: 2%;
  height: 2px;
  border: 0;
  background-color: grey;
}

.writebutton {
  background-color: #ffae52;
  color: white;
  padding: 14px 20px;
  margin-top: 40px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 12px;
  font-style: bold;
  float: right;
}

.header1 {
  background-color: rgb(218, 231, 255);
  height: 50px;
  font-size: 12px;
  text-align: center;
}

.header1 .td {
  width: 10%;
}

.header2 {
  font-size: 12px;
}

.allpost {
  text-align: center;
}

</style>