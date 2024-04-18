<script setup>
import Header from './components/Main/Header.vue';
import Footer from './components/Main/Footer.vue';

import Chatbot from './components/Chatbot/Chatbot.vue'

import { RouterView } from 'vue-router';
import { provide, ref, reactive } from 'vue';

const isLoggedin = ref(false);
provide('isLoggedin', isLoggedin);

const globalState = reactive({
    isLoggedin: false
});

provide('globalState', globalState);
</script>

<template>
        <Header></Header>
    <main class="container">
        <div class="main1">
            <RouterView />
        </div>
        <div class="main2">
            <Chatbot></Chatbot>
        </div>
    </main>
    <Footer></Footer>
</template>

<style>
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
}

* {
    font-family: GmarketSansMedium;
}

.container {
    display: flex;
    align-items: center;
    justify-content: center;
}

.main1 {
    flex: 1.5;
    min-width: 200px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-right: -7%;
}

.main2 {
    flex: 0.5;
    display: flex;
    align-items: center;
    justify-content: center;
}

html, body {
    margin: 0 !important;
    padding-top: 0 !important;
    padding-bottom: 0 !important;
    padding-left: 10px !important;
    padding-right: 10px !important;
    overflow-x: hidden;
}
</style>