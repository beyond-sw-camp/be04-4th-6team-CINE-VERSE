<template>
    <div class="container">
        <div class="banner">
            <div class="bannerimg">
                <img src="@/assets/img/mainpagebanner.png" class="mainbannerimage">
            </div>
            <div class="content">
                <div class="likepost">
                <button type="button" class="likepostbtn" @click="likepost">인기 이벤트</button>
                    <div class="likepostbox">인기 이벤트게시글 10개</div>
                </div>
                <div class="allpost">
                    <button type="button" class="allpostbtn" @click="allpost">인기 게시글</button>
                    <div class="allpostbox">인기 자유게시글 10개</div>
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
</script>

<style scoped>
@import url('@/assets/css/main/Main.css');
</style>