<template>
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
                <button onclick="" class="writebutton">게시글 작성</button>
            </td>
        </tr>
        <tr class="header2">
            <td>정렬기준</td>
            <td>작성일자</td>
            <td>내림차순</td>
            <td>작성자</td>
            <td>searchbox</td>
        </tr>
        <!-- <hr class="titleLine"> -->
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
      <tr v-for="(review, index) in reviews" :key="review.review_id" class="allpost">
        <td>{{ index + 1 }}</td>
        <td class="boardname" @click="changeRouter(review.review_id)">{{ review.reviewsTitle }}</td>
        <td>{{ review.review_category_id }}</td>
        <td>{{ review.infoViewCount }}</td>
        <td>{{ review.memberId }}</td>
        <td>{{ review.infoDate }}</td>
      </tr>
    </tbody>
  </table>
</template>
<script setup>
    import { RouterLink, RouterView } from 'vue-router';
    import axios from "axios";
    import { onMounted, ref } from "vue";
    import router from '@/router/mainRouter';
    import { useRoute } from 'vue-router';

    const review = ref([]);
    const index = 1;

    onMounted(async () =>{
        axios.get("http://localhost:8081/review/list")
        .then(response => {
            info.value = response.data;
            console.log(review.value);
            console.log(review.value[0].postTitle);
        })
    });


    function changeRouter(id) {
        const reviewId = id;
        console.log(reviewId);
        router.push(`/review/${boardId}`)
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
        font-size:32px;
        margin-top: 50px;
    }

    .titleLine {
            width:440%;
            margin-left: 2%;
            margin-right: 2%;
            height: 2px;
            border: 0;
            background-color: grey;
    }

    .writebutton{
        background-color: #8c52ff;
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

    .allpost{
        text-align: center;
    }
    
    
</style>