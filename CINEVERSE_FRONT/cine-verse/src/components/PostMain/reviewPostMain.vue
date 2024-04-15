<template>
    <div>
      <table class="table">
        <tr class="top">
          <td class="post">
            <h1 class="boardTitle">리뷰 게시판</h1>
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
    <tr v-for="(item, index) in filteredreview" :key="item.reviewId" class="allpost" @click="changeRouter(item.reviewId)">
      <td>{{ item.reviewId }}</td>
      <td class="boardname">{{ item.reviewTitle }}</td>
      <td>{{ item.reviewCategory.reviewCategory }}</td>
      <td>{{ item.reviewViewCount }}</td>
      <td>{{ item.postLike }}</td>
      <td>{{ item.member.nickname }}</td>
      <td>{{ item.reviewDate }}</td>
    </tr>
  </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { onMounted, ref, watch } from "vue";
  import axios from "axios";
  import router from "@/router/mainRouter";
  
  const review = ref([]);
  const index = 1;
  const search_condition = ref("");
  const search_type = ref("titleContent");
  const filteredreview = ref([]);
  
  const fetchreview = () => {
    axios.get(`http://localhost:8081/review_board/list`)
      .then(response => {
        review.value = response.data;
        filteredreview.value = response.data; // 초기에 전체 데이터를 filteredreview에 할당
      })
      .catch(error => {
        console.error("Error fetching review:", error);
      });
  };
  
  onMounted(() => {
    fetchreview();
  });
  
  function changeRouter(reviewId) {
    router.push(`/review_board/${reviewId}`);
  }
  
  function goToWritePage(){
    router.push(`/review_board/regist`);
  }
  
  // 검색 기능 구현
  watch([search_condition, search_type], () => {
    if (search_condition.value && search_type.value) {
      filterreview();
    } else {
      // 검색 조건이 비어있으면 전체 데이터를 보여줍니다.
      filteredreview.value = review.value;
    }
  });
  
  function filterreview() {
    const condition = search_condition.value.toLowerCase();
    const type = search_type.value;
  
    if (type === "titleContent") {
      filteredreview.value = review.value.filter(item =>
        item.reviewTitle.toLowerCase().includes(condition) ||
        item.reviewContent.toLowerCase().includes(condition)
      );
    } else if (type === "title") {
      filteredreview.value = review.value.filter(item =>
        item.reviewTitle.toLowerCase().includes(condition)
      );
    } else if (type === "content") {
      filteredreview.value = review.value.filter(item =>
        item.reviewContent.toLowerCase().includes(condition)
      );
    } else if (type === "writerId") {
      filteredreview.value = review.value.filter(item =>
        item.member.nickname.toLowerCase().includes(condition)
      );
    }
  }
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