<template>
    <div class="test">
        <div class="eventboardcontent">
            <div class="eventboard">
                <h1>이벤트 게시판</h1>
            </div>
            <div class="votecontent">
                <div class="votebtndiv">
                    <button class="votebtn">퀴즈</button>
                </div>
                <div class="voteboxesdiv">
                    <div class="voteboxdiv1"  v-if="events.length > 0">
                        <div class="votebox1">{{ events[0].title }}</div>
                        <div class="votebox2">{{ events[0].content }}
                            <button class="like1" @click="toggleLike(1)" :class="{ liked: isLiked[0] }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="voteboxdiv2">
                        <div class="votebox3">{{ events[1].title }}</div>
                        <div class="votebox4">{{ events[1].content }}
                            <button class="like2" @click="toggleLike(2)" :class="{ liked: isLiked[1] }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="voteboxdiv3">
                        <div class="votebox5">{{ events[2].title }}</div>
                        <div class="votebox6">{{ events[2].content }}
                            <button class="like3" @click="toggleLike(3)" :class="{ liked: isLiked[2] }">♥ 좋아요</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="quizcontent">
                <div class="quizbtndiv">
                    <!-- <button class="quizbtn">퀴즈</button> -->
                </div>
                <div class="quizboxesdiv">
                    <div class="quizboxdiv1">
                        <div class="quizbox1">{{ events[3].title }}</div>
                        <div class="quizbox2">{{ events[3].content }}
                            <button class="like4" @click="toggleLike(4)" :class="{ liked: isLiked[3] }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="quizboxdiv2">
                        <div class="quizbox3">{{ events[4].title }}</div>
                        <div class="quizbox4">{{ events[4].content }}
                            <button class="like5" @click="toggleLike(5)" :class="{ liked: isLiked[4] }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="quizboxdiv3">
                        <div class="quizbox5">{{ events[5].title }}</div>
                        <div class="quizbox6">{{ events[5].content }}
                            <button class="like6" @click="toggleLike(6)" :class="{ liked: isLiked[5] }">♥ 좋아요</button>
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

const info = ref([]);

const isLiked = ref(Array(6).fill(false));

function toggleLike(buttonNumber) {
    isLiked.value[buttonNumber - 1] = !isLiked.value[buttonNumber - 1];
}

const defaultEvent = { title: '이벤트 제목 없음', content: '이벤트 내용 없음' };

const events = computed(() => {
    const filledEvents = Array(6).fill(defaultEvent);
    const loadedEvents = info.value.map(event => ({
        title: event.eventTitle || '이벤트 제목 없음',
        content: event.eventContent || '이벤트 내용 없음',
    }));
    return loadedEvents.concat(filledEvents).slice(0, 6);
});

onMounted(async () => {
    try {
        const response = await axios.get("http://localhost:8081/event_board/list");
        // 날짜 기준 내림차순: 최신 이벤트가 배열 처음에
        info.value = response.data.sort((a, b) => new Date(b.eventDate) - new Date(a.eventDate));
    } catch (error) {
        console.error('이벤트 정보 전송 시에 에러 발생하였습니다.', error);
    }
});


</script>


<style scoped>
@import url('@/assets/css/PostMain/eventPostMain.css');
</style>