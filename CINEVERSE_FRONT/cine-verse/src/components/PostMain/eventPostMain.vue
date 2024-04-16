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
                    <div class="votebox-row" v-for="(chunk, chunkIndex) in chunkedEvents" :key="`chunk-${chunkIndex}`">
                        <div class="voteboxdiv" v-for="(event, index) in chunk" :key="event.eventBoardId">
                            <div class="votebox-title" @click="gotoEvent(event.eventBoardId)">
                                {{ event.title }}
                            </div>
                            <div class="votebox-content">
                                {{ event.content }}
                                <button class="like" @click="toggleLike(chunkIndex * 3 + index)"
                                    :class="{ liked: isLiked[chunkIndex * 3 + index] }">♥ 좋아요</button>
                            </div>
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

// 동적으로 좋아요 상태 배열 생성
const isLiked = ref([]);

function toggleLike(index) {
    isLiked.value[index] = !isLiked.value[index];
}

onMounted(async () => {
    try {
        const response = await axios.get("http://localhost:8081/event_board/list");
        info.value = response.data.sort((a, b) => new Date(b.eventDate) - new Date(a.eventDate));
        // 데이터 로딩 후 isLiked 초기화
        isLiked.value = Array(info.value.length).fill(false);
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

const chunkSize = 3;
const chunkedEvents = computed(() => {
    let chunks = [];
    for (let i = 0; i < events.value.length; i += chunkSize) {
        chunks.push(events.value.slice(i, i + chunkSize));
    }
    return chunks;
});
</script>



<style scoped>
@import url('@/assets/css/PostMain/eventPostMain.css');
</style>