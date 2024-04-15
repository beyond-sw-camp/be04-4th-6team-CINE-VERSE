<template>
    <div class="wrapper">
        <section>
            <div class="clickdiv" @click="mainBoard()">
                <h1 class="boardname">정보 게시판</h1> 
                <h2 class="boardcategory">{{ info.infoCategory.infoCategory }}</h2>
            </div>
            <hr class="boardtitleLine">
        </section>
        <div class="allboard" >
            <div class="boardtitlediv">
                <h2 class="boardtitle"> {{ info.infoTitle }}</h2>
            </div>
            <div class="datediv">
                <h3 class="date">작성일: {{ info.infoDate }}</h3>
            </div>
            <div class="writerdiv">
                <h3 class="writer">작성자: {{ info.member.nickname }}</h3>
            </div>
            <div class="viewandlike">
                <h3 class="viewandlike">조회수: {{ info.infoViewCount }}</h3>
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
                <div v-if="info.images && info.images.length > 0" class="image-container">
                    <img v-for="(image, index) in info.images" :key="index" :src="image.accessUrl" :alt="'Image ' + (index + 1)" class="info-image">
                </div>
                <div class="content">
                    <p v-html="info.infoContent"></p>
                </div>
            </div>
            <div class="like">
                <Like/>
            </div>
        <Reply/>
    </div>
</template>

<script setup>
    import router from '@/router/mainRouter';
    import Reply from './Reply.vue';
    import Like from './Like.vue';
    import axios from "axios";
    import { ref, onBeforeMount } from "vue";
    import { useRoute } from "vue-router";

    const info = ref({});
    const infoId = useRoute();

    const fetchInfo = () => {
        axios.get(`http://localhost:8081/info_board/${infoId.params.infoId}`)
        .then(response => {
            info.value = response.data;
        })
        .catch(error => {
            console.error("Error fetching info:", error);
        });
    }

    onBeforeMount(() => {
        fetchInfo();
    });

    function mainBoard() {
        router.push('/info_board/list');
    }

    function deletePost() {
        axios.patch(`http://localhost:8081/info_board/delete/${infoId.params.infoId}`)
        .then(response => {
            console.log('게시물이 성공적으로 삭제되었습니다.');
            router.push('/info_board/list');
        })
        .catch(error => {
            console.error("Error deleting post:", error);
        });
    }
    function editPost() {
    router.push(`/info_board/modify/${infoId.value.params.infoId}`);
}
</script>

<style scoped>
    @import url('@/assets/css/postAndReply/InfoPost.css');
</style>