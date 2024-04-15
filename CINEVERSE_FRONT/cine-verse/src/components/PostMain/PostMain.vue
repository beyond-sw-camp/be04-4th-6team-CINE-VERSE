<template>
  <div>
    <div class="table-container">
    <table class="table">
      <tr class="top">
        <td class="post" colspan="6">
    <h1 class="boardTitle">정보 게시판</h1>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td class="thing">
          <button @click="goToWritePage" class="writebutton">게시글 작성</button>
        </td>
      </tr>
      <tr class="header2" >
        <td></td>
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
        </td>
      </tr>
      <tr class="header1">
        <th class="num">게시글 번호</th>
        <td>게시글 제목</td>
        <td>카테고리</td>
        <td>조회수</td>
        <td>작성자</td>
        <td>작성일자</td>
      </tr>
      <tbody>
  <tr v-for="(item, index) in filteredInfo" :key="item.infoId" class="allpost" @click="changeRouter(item.infoId)">
    <td class="num">{{ item.infoId }}</td>
    <td class="boardname">{{ item.infoTitle }}</td>
    <td>{{ item.infoCategory.infoCategory }}</td>
    <td>{{ item.infoViewCount }}</td>
    <td>{{ item.member.nickname }}</td>
    <td>{{ item.infoDate }}</td>
  </tr>
</tbody>
    </table>
  </div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from "vue";
import axios from "axios";
import router from "@/router/mainRouter";

const info = ref([]);
const index = 1;
const search_condition = ref("");
const search_type = ref("titleContent");
const filteredInfo = ref([]);

const fetchInfo = () => {
  axios.get(`http://localhost:8081/info_board/list`)
    .then(response => {
      info.value = response.data;
      filteredInfo.value = response.data; // 초기에 전체 데이터를 filteredInfo에 할당
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

// 검색 기능 구현
watch([search_condition, search_type], () => {
  if (search_condition.value && search_type.value) {
    filterInfo();
  } else {
    // 검색 조건이 비어있으면 전체 데이터를 보여줍니다.
    filteredInfo.value = info.value;
  }
});

function filterInfo() {
  const condition = search_condition.value.toLowerCase();
  const type = search_type.value;

  if (type === "titleContent") {
    filteredInfo.value = info.value.filter(item =>
      item.infoTitle.toLowerCase().includes(condition) ||
      item.infoContent.toLowerCase().includes(condition)
    );
  } else if (type === "title") {
    filteredInfo.value = info.value.filter(item =>
      item.infoTitle.toLowerCase().includes(condition)
    );
  } else if (type === "content") {
    filteredInfo.value = info.value.filter(item =>
      item.infoContent.toLowerCase().includes(condition)
    );
  } else if (type === "writerId") {
    filteredInfo.value = info.value.filter(item =>
      item.member.nickname.toLowerCase().includes(condition)
    );
  }
}
</script>

<style scoped>
/* 테이블 컨테이너 */
.table-container {
  width: 180%;
  max-width: 180%;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: 20px;
  display: flex;
  justify-content: center; /* 중앙 정렬 */
}

/* 테이블 */
.table {
  width: 200%;
  max-width: 200%;
  border-collapse: collapse;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* 게시글 제목 */
.boardname {
  text-decoration: none;
  color: black;
  cursor: pointer;
}

/* 게시판 제목 */
.boardTitle {
  font-size: 32px;
  margin-top: 20px; /* 이전과 일관성을 위해 조정 */
  margin-bottom: 20px; /* 이전과 일관성을 위해 조정 */
  text-align: center; /* 가운데 정렬 */
}
.search {
  display: flex;
  align-items: center;
}

.search select, .search input[type="text"], .searchBtn {
  margin-right: 10px; /* 수정: 각 요소 사이 여백 조정 */
}

.searchBtn {
  background-color: #007bff; /* 수정: 검색 버튼 색상 변경 */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  padding: 8px 16px; /* 수정: 버튼 내부 여백 조정 */
  font-size: 14px;
}
/* 구분선 */
.titleLine {
  width: 100%; /* 테이블 너비에 맞게 조정 */
  height: 2px;
  border: 0;
  background-color: grey;
}

/* 글 작성 버튼 */
.writebutton {
  background-color: #ffae52;
  color: white;
  padding: 10px 15px; /* 크기 조정 */
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px; /* 크기 조정 */
  font-weight: bold; /* 굵게 설정 */
  margin-top: 20px;
  margin-right: 20px; /* 우측 여백 추가 */
}

/* 헤더 */
.header1 {
  background-color: #f8e1c7;
  height: 50px;
  font-size: 12px;
  text-align: center;
}

.header1 .td {
  width: 10px;
  border-right: 1px solid #020000;
}

/* 헤더2 */
.header2 {
  font-size: 12px;
}
.header1 .num {
  width: 5%;
}

/* 전체 게시물 */
.allpost {
  text-align: center;
  padding: 10px 0; /* 위아래 여백 추가 */
}
.allpost .num {
  width: 5%;
}
.allpost td {
  border-right: 1px solid #ddd;
}
</style>