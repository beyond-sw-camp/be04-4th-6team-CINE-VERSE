<template>
    <div class="container">
        <div class="banner">
            <div class="bannerimg">
                <img src="@/assets/img/mainpagebanner.png" class="mainbannerimage">
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
                            <div class="eventposttitle">{{ event.eventTitle }}</div>
                            <div class="eventpostcontent">{{ event.eventContent }}</div>
                            <div class="eventpostnickname">{{ event.member.nickname }}</div>
                            <div class="eventpostdate">{{ event.eventDate }}</div>
                            <div class="eventpostviews">{{ event.eventViewCount }}</div>
                        </li>
                    </div>
                </div>
                <div class="freepost">
                    <h3 class="freepostbtn">최신 자유 게시글</h3>
                    <div class="freepostbox">자유게시글 10개</div>
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
import { ref, onMounted } from 'vue';
import axios from 'axios';

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
        latestEvents.value = sortedEvents.slice(0, 5);
    } catch (error) {
        console.error('최신 이벤트 게시글을 가져오는데 실패했습니다.', error);
    }
});
</script>

<style scoped>
@import url('@/assets/css/main/Main.css');
</style>