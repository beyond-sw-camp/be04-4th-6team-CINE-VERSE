<template>
    <div class="wrapper">
        <section>
            <div class="clickdiv" @click="mainBoard()">
                <h1 class="boardname">자유 게시판</h1> 
            </div>
            <hr class="boardtitleLine">
        </section>
        <div class="allboard" >
            <div class="boardtitlediv">
                <h2 class="boardtitle"> {{ free.freeTitle }}</h2>
            </div>
            <div class="datediv">
                <h3 class="date">{{ free.freeDate }}</h3>
            </div>
            <div class="like">
           <button type="button" @click="editPost">
                수정
            </button>
        </div>
            <div class="writerdiv">
                <h3 class="writer">{{ free.member.nickname }}</h3>
            </div>
            <div class="viewandlike">
                <h3 class="viewandlike">{{ free.freeViewCount }}</h3>
            </div>
        </div>
        <hr class="titleLine">
        <div class="maincontent">
            <p v-html="free.freeContent"></p>
            <div v-if="free.images && free.images.length > 0" class="image-container">
                <img v-for="(image, index) in free.images" :key="index" :src="image.accessUrl" :alt="'Image ' + (index + 1)" class="free-image">
            </div>
        </div>
        <Like/>
        <div class="like">
           <button type="button" @click="deletePost">
                삭제
            </button>
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

    const free = ref({});
    const freeId = useRoute();

    const fetchfree = () => {
        axios.get(`http://localhost:8081/free_board/${freeId.params.freeId}`)
        .then(response => {
            free.value = response.data;
        })
        .catch(error => {
            console.error("Error fetching free:", error);
        });
    }

    onBeforeMount(() => {
        fetchfree();
    });

    function mainBoard() {
        router.push('/free_board/list');
    }

    function deletePost() {
        axios.patch(`http://localhost:8081/free_board/delete/${freeId.params.freeId}`)
        .then(response => {
            console.log('게시물이 성공적으로 삭제되었습니다.');
            router.push('/free_board/list');
        })
        .catch(error => {
            console.error("Error deleting post:", error);
        });
    }
    function editPost() {
    router.push(`/free_board/modify/${freeId.value.params.freeId}`);
}
</script>

<style scoped>
@import url('@/assets/css/postAndReply/freePost.css');   
</style>