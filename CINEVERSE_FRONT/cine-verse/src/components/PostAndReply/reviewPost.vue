<template>
    <div class="wrapper">
        <section>
            <div class="clickdiv" @click="mainBoard()">
                <h1 class="boardname">정보 게시판</h1> 
                <h2 class="boardcategory">{{ review.reviewCategory.reviewCategory }}</h2>
            </div>
            <hr class="boardtitleLine">
        </section>
        <div class="allboard" >
            <div class="boardtitlediv">
                <h2 class="boardtitle"> {{ review.reviewTitle }}</h2>
            </div>
            <div class="datediv">
                <h3 class="date">{{ review.reviewDate }}</h3>
            </div>
            <div class="like">
           <button type="button" @click="editPost">
                수정
            </button>
        </div>
            <div class="writerdiv">
                <h3 class="writer">{{ review.member.nickname }}</h3>
            </div>
            <div class="viewandlike">
                <h3 class="viewandlike">{{ review.reviewViewCount }}</h3>
            </div>
        </div>
        <hr class="titleLine">
        <div class="maincontent">
            <p v-html="review.reviewContent"></p>
            <div v-if="review.images && review.images.length > 0" class="image-container">
                <img v-for="(image, index) in review.images" :key="index" :src="image.accessUrl" :alt="'Image ' + (index + 1)" class="review-image">
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

    const review = ref({});
    const reviewId = useRoute();

    const fetchreview = () => {
        axios.get(`http://localhost:8081/review_board/${reviewId.params.reviewId}`)
        .then(response => {
            review.value = response.data;
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
        .clickdiv {
            width: 20%;
        }

        .boardtitleLine {
            width:100%;
            height: 3px;
            border: 0;
            background-color: #e9740e;
        }

        .titleLine {
            width:95%;
            margin-left: 2%;
            margin-right: 2%;
            height: 2px;
            border: 0;
            background-color:  #e9740e;
        }

        .wrapper {
            height: auto;
            min-height: 100%;
            width: 68%;
            margin-top: 50px;
            padding-bottom: 100px;
            margin-left: 16%;
            margin-right: 16%;
        }

        .boardname {
            font-size: 32px;
            cursor: pointer;
        }

        .boardtitle {
            font-size: 20px;
            margin-top: 20px;  
        }

        .writer {
            font-size: 12px;
            margin-top: 30px;
        }

        .date {
            font-size: 10px;
            margin-top: 5px;
        }

        .allboard {
            display: flex;
            flex-direction: rows;
            justify-content: center;
            width: 100%;
        }

        .boardtitlediv {
            padding-left: 10px;
            padding-right: 10px;
            margin-left: 0%;
            width: 70%;
        }

        .writerdiv {
            width: 15%;
        }

        .datediv {
            width:20%;
            margin-right:2%;
            float:right;
        }
        .viewandlike{
            font-size: 12px;
            margin-top: 30px;
            padding-right: 10px;
            margin-right: 2%;
            float:right;
        }

        

        .maincontent {
            max-width: 80%;
            margin-left: 10%;
            margin-right: 10%;
            margin-top: 3%;
        }
        .like{
            display:inline-block ;
            margin-left: 30%;
            margin-right: 10%;
            margin-top: 10%;
        }

        

        
</style>