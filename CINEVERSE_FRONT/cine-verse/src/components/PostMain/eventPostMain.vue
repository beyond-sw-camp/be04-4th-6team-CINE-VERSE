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
                    <div class="voteboxdiv1" v-if="events.length > 0" @click="gotoEvent(events[0].eventBoardId)">
                        <div class="votebox1">{{ events[0].title }}</div>
                        <div class="votebox2">{{ events[0].content }}
                            <button class="like1" @click="toggleLike(1)" :class="{ liked: isLiked[0] }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="voteboxdiv2" v-if="events.length > 1" @click="gotoEvent(events[1].eventBoardId)">
                        <div class="votebox3">{{ events[1].title }}</div>
                        <div class="votebox4">{{ events[1].content }}
                            <button class="like2" @click="toggleLike(2)" :class="{ liked: isLiked[1] }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="voteboxdiv3" v-if="events.length > 2" @click="gotoEvent(events[2].eventBoardId)">
                        <div class="votebox5">{{ events[2].title }}</div>
                        <div class="votebox6">{{ events[2].content }}
                            <button class="like3" @click="toggleLike(3)" :class="{ liked: isLiked[2] }">♥ 좋아요</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="quizcontent" v-if="events.length > 3">
                <div class="quizbtndiv">
                    <!-- <button class="quizbtn">투표</button> -->
                </div>
                <div class="quizboxesdiv">
                    <div class="quizboxdiv1" v-if="events.length > 3" @click="gotoEvent(events[3].eventBoardId)">
                        <div class="quizbox1">{{ events[3].title }}</div>
                        <div class="quizbox2">{{ events[3].content }}
                            <button class="like4" @click="toggleLike(4)" :class="{ liked: isLiked[3] }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="quizboxdiv2" v-if="events.length > 4" @click="gotoEvent(events[4].eventBoardId)">
                        <div class="quizbox3">{{ events[4].title }}</div>
                        <div class="quizbox4">{{ events[4].content }}
                            <button class="like5" @click="toggleLike(5)" :class="{ liked: isLiked[4] }">♥ 좋아요</button>
                        </div>
                    </div>
                    <div class="quizboxdiv3" v-if="events.length > 5" @click="gotoEvent(events[5].eventBoardId)">
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

onMounted(async () => {
    try {
        const response = await axios.get("http://localhost:8081/event_board/list");
        // 날짜 기준 내림차순
        info.value = response.data.sort((a, b) => new Date(b.eventDate) - new Date(a.eventDate));
    } catch (error) {
        console.error('이벤트 정보 전송 시에 에러 발생하였습니다.', error);
    }
});

const events = computed(() => {
    return info.value.map(event => ({
        eventBoardId: event.eventBoardId, 
        title: event.eventTitle || '이벤트 제목 없음',
        content: event.eventContent || '이벤트 내용 없음',
    }));
});

function gotoEvent(eventBoardId) {
    if (eventBoardId) {
        router.push(`/event_board/${eventBoardId}`).catch(err => {
            if (err.name !== 'NavigationDuplicated') {
                console.error(err);
            }
        });
    } else {
        console.error('eventBoardId가 정의되지 않았습니다.');
    }
}


</script>


<style scoped>
@import url('@/assets/css/PostMain/eventPostMain.css');
</style>