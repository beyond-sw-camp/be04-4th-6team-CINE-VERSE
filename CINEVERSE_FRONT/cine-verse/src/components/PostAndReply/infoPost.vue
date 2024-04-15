<template>
    <div class="wrapper">
        <section>
            <div class="clickdiv" @click="mainBoard()">
                <h1 class="boardname">정보 게시판</h1> 
                <h2 class="boardcategory">축전</h2>
            </div>
            <hr class="boardtitleLine">
        </section>
        <div class="allboard" >
            <div class="boardtitlediv">
                <h2 class="boardtitle"> {{ info.infoTitle }}</h2>
            </div>
            <div class="datediv">
                <h3 class="date">{{ info.infoDate }}</h3>
            </div>
            <div class="like">
           <button type="button" @click="editPost">
                수정
            </button>
        </div>
            <div class="writerdiv">
                <h3 class="writer">{{ info.member.memberName }}</h3>
            </div>
            <div class="viewandlike">
                <h3 class="viewandlike">{{ info.infoViewCount }} 33</h3>
            </div>
        </div>
        <hr class="titleLine">
        <div class="maincontent">
            <p v-html="info.infoContent"></p>
        </div>
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