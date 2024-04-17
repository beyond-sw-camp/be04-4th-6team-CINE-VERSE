<template>
    <div class="container">
        <div class="banner">
            <div class="bannerimg">
                <img src="@/assets/img/mainpagebanner2.png" class="mainbannerimage">
            </div>
            <div class="content">
                <div class="eventpost" v-if="latestEvents.length > 0">
                    <h3 class="eventpostbtn">최신 이벤트 게시글</h3>
                    <div class="eventpostheader">
                        <div class="eventpostheadertitle">제목</div>
                        <div class="eventpostheadercontent">내용</div>
                        <div class="eventpostheadernickname">닉네임</div>
                        <div class="eventpostheaderdate">작성일</div>
                        <div class="eventpostheaderviews">조회수</div>
                    </div>
                    <div class="eventpostbox">
                        <li v-for="(event, index) in latestEvents" :key="index" class="eventpostrow">
                            <div class="eventposttitle" @click="gotoEvent(event.eventBoardId)">{{ event.eventTitle }}</div>
                            <div class="eventpostcontent" @click="gotoEvent(event.eventBoardId)">{{ event.eventContent }}</div>
                            <div class="eventpostnickname">{{ event.member.nickname }}</div>
                            <div class="eventpostdate">{{ event.eventDate }}</div>
                            <div class="eventpostviews">{{ event.eventViewCount }}</div>
                        </li>
                    </div>
                </div>
                <div class="freepost"  v-if="latestFreePosts.length > 0">
                    <h3 class="freepostbtn">최신 자유 게시글</h3>
                    <div class="freepostheader">
                        <div class="freepostheadertitle">제목</div>
                        <div class="freepostheadercontent">내용</div>
                        <div class="freepostheadernickname">닉네임</div>
                        <div class="freepostheaderdate">작성일</div>
                        <div class="freepostheaderviews">조회수</div>
                    </div>
                    <div class="freepostbox">
                        <li v-for="(post, index) in latestFreePosts" :key="index" class="freepostrow">
                            <div class="freeposttitle" @click="gotoFree(post.freeId)">{{ post.freeTitle }}</div>
                            <div class="freepostcontent" @click="gotoFree(post.freeId)">{{ post.freeContent }}</div>
                            <div class="freepostnickname">{{ post.member.nickname }}</div>
                            <div class="freepostdate">{{ post.freeDate }}</div>
                            <div class="freepostviews">{{ post.freeViewCount }}</div>
                        </li>
                    </div>
                </div>
            </div>
            <div class="movie-rankings-container">
                <div class="movie-rankings">
                    <h2>👑 실시간 인기 영화 순위 👑</h2>
                    <ul>
                        <li v-for="(movie, index) in movies" :key="index">
                            {{ index + 1 }}. {{ movie['영화 제목'] }} ({{ movie['예매율'] }})
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import router from '@/router/mainRouter';

const movies = ref([]);

onMounted(() => {
    fetch('top_10_movies.json')
        .then(response => response.json())
        .then(data => {
            movies.value = Object.values(data);
        })
        .catch(error => {
            console.error("Could not load the movie rankings:", error);
        });
});


const latestEvents = ref([]);

onMounted(async () => {

    try {
        const response = await axios.get("http://localhost:8081/event_board/list");
        const sortedEvents = response.data.sort((a, b) => new Date(b.eventDate) - new Date(a.eventDate));
        latestEvents.value = sortedEvents.slice(0, 10);
        console.log(latestEvents.value);
    } catch (error) {
        console.error('최신 이벤트 게시글을 가져오는데 실패했습니다.', error);
    }
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


const latestFreePosts = ref([]);

onMounted(async () => {

    try {
        const freePostResponse = await axios.get("http://localhost:8081/free_board/list");
        const sortedFreePosts = freePostResponse.data.sort((a, b) => new Date(b.freeDate) - new Date(a.freeDate));
        latestFreePosts.value = sortedFreePosts.slice(0, 10);
        console.log(latestFreePosts.value);
    } catch (error) {
        console.error('최신 자유 게시글을 가져오는데 실패했습니다.', error);
    }
});

function gotoFree(freeId) {
    if (freeId) {
        router.push(`/free_board/${freeId}`).catch(err => {
            if (err.name !== 'NavigationDuplicated') {
                console.error(err);
            }
        });
    } else {
        console.error('freeId가 정의되지 않았습니다.');
    }
}

</script>

<style scoped>
@import url('@/assets/css/main/Main.css');
</style>