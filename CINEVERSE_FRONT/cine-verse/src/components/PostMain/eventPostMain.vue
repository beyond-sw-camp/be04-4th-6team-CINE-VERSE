<template>
    <div class="test">
        <div class="eventboardcontent">
            <div class="eventboard">
                <h1>이벤트 게시판</h1>
            </div>
            <div class="votecontent">
                <div class="votebtndiv">
                    <button class="votebtn">투표</button>
                </div>
                <div class="voteboxesdiv">
                    <div class="voteboxdiv1">
                        <div class="votebox1">{{ eventTitle }}</div>
                        <div class="votebox2">{{ eventContent }}
                            <button class="like1" @click="toggleLike(1)" :class="{ liked: isLiked1 }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="voteboxdiv2">
                        <div class="votebox3">투표 제목</div>
                        <div class="votebox4">투표 2
                            <button class="like2" @click="toggleLike(2)" :class="{ liked: isLiked2 }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="voteboxdiv3">
                        <div class="votebox5">투표 제목</div>
                        <div class="votebox6">투표 3
                            <button class="like3" @click="toggleLike(3)" :class="{ liked: isLiked3 }">♥ 좋아요</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="quizcontent">
                <div class="quizbtndiv">
                    <button class="quizbtn">퀴즈</button>
                </div>
                <div class="quizboxesdiv">
                    <div class="quizboxdiv1">
                        <div class="quizbox1">퀴즈 제목</div>
                        <div class="quizbox2">퀴즈 1
                            <button class="like4" @click="toggleLike(4)" :class="{ liked: isLiked4 }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="quizboxdiv2">
                        <div class="quizbox3">퀴즈 제목</div>
                        <div class="quizbox4">퀴즈 2
                            <button class="like5" @click="toggleLike(5)" :class="{ liked: isLiked5 }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="quizboxdiv3">
                        <div class="quizbox5">퀴즈 제목</div>
                        <div class="quizbox6">퀴즈 3
                            <button class="like6" @click="toggleLike(6)" :class="{ liked: isLiked6 }">♥ 좋아요</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from "axios";
import { onMounted, ref, computed } from "vue";
import router from '@/router/mainRouter';

const isLiked1 = ref(false);
const isLiked2 = ref(false);
const isLiked3 = ref(false);
const isLiked4 = ref(false);
const isLiked5 = ref(false);
const isLiked6 = ref(false);

function toggleLike(buttonNumber) {
    if (buttonNumber === 1) {
        isLiked1.value = !isLiked1.value;
    } else if (buttonNumber === 2) {
        isLiked2.value = !isLiked2.value;
    } else if (buttonNumber === 3) {
        isLiked3.value = !isLiked3.value;
    } else if (buttonNumber === 4) {
        isLiked4.value = !isLiked4.value;
    } else if (buttonNumber === 5) {
        isLiked5.value = !isLiked5.value;
    } else if (buttonNumber === 6) {
        isLiked6.value = !isLiked6.value;
    }
}

const info = ref([]);
const eventInfo = ref(0);

const eventTitle = computed(() => {
    if (info.value.length > eventInfo.value) {
        return info.value[eventInfo.value].eventTitle || '이벤트 제목 없음';
    }
    return '이벤트 로딩 중...';
});

const eventContent = computed(() => {
    if (info.value.length > eventInfo.value) {
        return info.value[eventInfo.value].eventContent || '이벤트 내용 없음';
    }
    return '이벤트 로딩 중...';
});

onMounted(async () => {
    try {
        const response = await axios.get("http://localhost:8081/event_board/list");
        info.value = response.data;
        console.log(info.value);
    } catch (error) {
        console.error('이벤트 정보 전송 시에 에러 발생하였습니다.', error);
    }
});


// function changeRouter(id) {
//     const eventId = id;
//     console.log(eventId);
//     router.push(`/event_board/${eventId}`)
// }
</script>


<style scoped>
@import url('@/assets/css/PostMain/eventPostMain.css');
</style>