
<template>
    <div class="wrapper">
        <section>
            <div class="clickdiv" @click="mainBoard()">
                <h1 class="boardname">정보 게시판</h1> 
                <h2 class="boardcategory">축전</h2>
            </div>
            <hr class="boardtitleLine">
        </section>
        <div class="allboard">
            <div class="boardtitlediv">
                <h2 class="boardtitle"> {{ info.result.info_title }}</h2>
            </div>
            <div class="datediv">
                <h3 class="date">{{info.result.info_date}}</h3>
            </div>
            <div class="writerdiv">
                <h3 class="writer">{{ info.result.member_id }}</h3>
            </div>
            <div class="viewandlike">
                <h3 class="viewandlike">444 33</h3>
                </div>
        </div>
        <hr class="titleLine">
        <div class="maincontent">
            <p v-html="info.result.info_content">
            </p>
        </div>
        <div class="like">
            <button type="button" onclick="window.location.href='링크 주소'">
                <img src="@/assets/img/logo.png">
            </button>
           
        </div>

        <Reply/>
    </div>
</template>

<script setup>
    import router from '@/router/mainRouter';
    import Reply from './Reply.vue';
    import axios from "axios";
    import { ref, onBeforeMount } from "vue";
    import {useRoute} from "vue-router";

    const info = ref({
        result: {}
    });

    const infoId = useRoute();

    const fetchInfo = () => {
        axios.get(`http://localhost:8081/info_board/${infoId.params.infoId}`)
        .then(response => {
            info.value = response.data;
            console.log('info', info);
            console.log('info.result', info.result);
            console.log(response);
            console.log(info.value);
            console.log(info.value.result.postTitle);
        })
    }

onBeforeMount(() => {
    fetchInfo();    
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