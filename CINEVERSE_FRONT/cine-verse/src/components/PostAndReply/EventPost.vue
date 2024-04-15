<template>
    <div class="wrapper">
        <section>
            <div class="clickdiv" @click="mainBoard()">
                <h1 class="boardname">자유 게시판</h1> 
                <h2 class="boardcategory">축전</h2>
            </div>
            <hr class="boardtitleLine">
        </section>
        <div class="allboard">
            <div class="boardtitlediv">
                <h2 class="boardtitle"> {{ posts.result.postTitle }}</h2>
            </div>
            <div class="datediv">
                <h3 class="date">{{posts.result.postDate}}</h3>
            </div>
            <div class="writerdiv">
                <h3 class="writer">{{ posts.result.memberNickname }}</h3>
            </div>
            <div class="viewandlike">
                <h3 class="viewandlike">444 33</h3>
                </div>
        </div>
        <hr class="titleLine">
        <div class="maincontent">
            <p v-html="posts.result.postContent">
            </p>
        </div>
        <section class="maincontentcontainer1">
            <h1 class="quiztitle">퀴즈1</h1>
            <span class="quiz"><p>the must have item you have</p></span>
            <div class="quizcontent">
                <h3 class="quizsubtitle">힌트</h3>
                <br><br>
                <p>힌트를 보고 싶으면 100포인트 줘</p>
            </div>
            <br><br>
            <p class="quizanswer">정답은?</p>
        </section>
        <br><br>
        <section class="maincontentcontainer1">
            <h1 class="quiztitle">퀴즈2</h1>
            <span class="quiz"><p>the must have item you have</p></span>
            <div class="quizcontent">
                <h3 class="quizsubtitle">힌트</h3>
                <br><br>
                <p>힌트를 보고 싶으면 100포인트 줘</p>
            </div>
            <br><br>
            <p class="quizanswer">정답은?</p>
        </section>
           
        </div>

        <Reply/>
   
</template>

<script setup>
    import router from '@/router/mainRouter';
    import Reply from './Reply.vue';
    import axios from "axios";
    import { ref, onBeforeMount } from "vue";
    import {useRoute} from "vue-router";

    const posts = ref({
        result: {}
    });

    const postId = useRoute();

    const fetchPosts = () => {
        axios.get(`http://localhost:800/post/content/${postId.params.id}`)
        .then(response => {
            posts.value = response.data;
            console.log('post', posts);
            console.log('post.result', posts.result);
            console.log(response);
            console.log(posts.value);
            console.log(posts.value.result.postTitle);
        })
    }
    onBeforeMount(() => {
        fetchPosts();    
    })

    function mainBoard() {
        router.push('/info_board/list');
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

        .maincontentcontainer1 {
        display: flex;
        flex-direction: column; /* 세로 방향으로 요소들을 배치 */
        align-items: center;
        width: 800px;
        height: 160px;
        background: #f5bf90;
        }
        
        .quiztitle {
            color: #e9740e;
            align-self: start;
            
        }
        .quiz{
            align-self: start;
        }
        .quizcontent2 {
            color: black;
        }
        .quizcontent {
            display: block;
            width: 600px;
            height: 50px;
            background: #f3efec;
            border-width: 2px;
            border-style: solid;
            border-color: #e94c0e;
        }



        .quizsubtitle {
            margin-top: 5px;
            margin-left: 0%;
            float: left;
            color: #e9740e;
        }

        .quizanswer {
            display: block;
            width: 600px;
            height: 20px;
            background: #f3efec;
            border-width: 2px;
            border-style: solid;
            border-color: #e94c0e;
        }
        .like{
            display:inline-block ;
            margin-left: 30%;
            margin-right: 10%;
            margin-top: 10%;
        }

        

        
</style>