<template>
    <div class="wrapper">
            <div class="review-board">
                <div class="clickdiv" @click="mainBoard()">
                    <h1 class="boardname">리뷰 게시판</h1> 
                </div>
            <div class="category">
                <h2 class="boardcategory">{{ review.reviewCategory.reviewCategory }}</h2>
            </div>
        </div>
            <hr class="boardtitleLine">
        <div class="allboard" >
            <div class="boardtitlediv">
                <h2 class="boardtitle">{{ review.reviewTitle }}</h2>
            </div>
            <div class="datediv">
                <h3 class="date">작성일: {{ review.reviewDate }}</h3>
            </div>
            <div class="writerdiv">
                <h3 class="writer">작성자: {{ review.member.nickname }}</h3>
            </div>
            <div class="viewandlike">
                <h3 class="viewandlike">조회수: {{ review.reviewViewCount }}</h3>
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
            <div v-if="review.images && review.images.length > 0" class="image-container">
                <img v-for="(image, index) in review.images" :key="index" :src="image.accessUrl" :alt="'Image ' + (index + 1)" class="review-image">
            </div>
            <div class="content">
                <p v-html="review.reviewContent"></p>
            </div>
        </div>
        <div class="like">
            <Like/>
        </div>
        <Replyy/>
    </div>
</template>

<script setup>
    import router from '@/router/mainRouter';
    import Replyy from './Replyy.vue';
    import Like from './Like.vue';
    import axios from "axios";
    import { ref, onBeforeMount } from "vue";
    import { useRoute } from "vue-router";

    const review = ref({});
    const reviewId = useRoute();

    const fetchreview = () => {
        axios.get(`http://localhost:8081/review_board/${reviewId.params.reviewId}`)
        .then(response => {
            review.value = response.data;
            console.log('서버로부터 받은 정보:', response.data);
        })
        .catch(error => {
            console.error("Error fetching review:", error);
        });
    }

    onBeforeMount(() => {
        fetchreview();
    });

    function mainBoard() {
        router.push('/review_board/list');
    }

    function deletePost() {
        axios.patch(`http://localhost:8081/review_board/delete/${reviewId.params.reviewId}`)
        .then(response => {
            console.log('게시물이 성공적으로 삭제되었습니다.');
            router.push('/review_board/list');
        })
        .catch(error => {
            console.error("Error deleting post:", error);
        });
    }
    function editPost() {
    router.push(`/review_board/modify/${reviewId.value.params.reviewId}`);
}
</script>

<style scoped>
@import url('@/assets/css/postAndReply/reviewPost.css');  
</style>