<template>
    <header class="header">
        <div class="logo">
            <img src="@/assets/img/logo.png" class="logoimage" @click="main">
        </div>
        <div class="menu">
            <button type="button" class="infobtn" @click="info()">정보</button>
            <button type="button" class="reviewbtn" @click="review()">리뷰</button>
            <button type="button" class="eventbtn" @click="event()">이벤트</button>
            <button type="button" class="freebtn" @click="free()">자유</button>
            <button type="button" class="qnabtn" @click="qna()">문의</button>
        </div>
        <div class="btnbox">
            <div class="Badgeimg">
                <img src="@/assets/img/spidermanbadge.jpg" class="badgeimage" @click="badge">
            </div>
            <div class="Profile">
                <img src="@/assets/img/profile.png" class="profileimage" @click="profile">
            </div>
            <div class="joinbtndiv">
                <button type="button" class="joinbtn" @click="join()">회원가입</button>
            </div>
            <div class="loginbtndiv">
                <button v-if="!isLoggedin" type="button" class="loginbtn" @click="login">로그인</button>
                <button v-else type="button" class="logoutbtn" @click="logout">로그아웃</button>
            </div>
        </div>
    </header>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { inject, onMounted } from 'vue';

const router = useRouter();
const isLoggedin = inject('isLoggedin');

onMounted(() => {
    isLoggedin.value = !!localStorage.getItem('sessionId'); 
});

function logout() {
    localStorage.removeItem('sessionId');
    alert('로그아웃 되었습니다.');
    router.push('/main');
    isLoggedin.value = false;
}

function join() {
    router.push("/member/regist");
}

function login() {
    router.push("/member/login");
}

function info() {
    router.push("/info_board/list");
}

function review() {
    router.push("/review_board/list");
}

function event() {
    router.push("/event_board/list");
}

function free() {
    router.push("/free_board/list");
}

function qna() {
    router.push("/");
}

function main() {
    router.push("/");
}

function profile() {
    const memberIdCookie = document.cookie
        .split('; ')
        .find(row => row.startsWith('memberId='));
    const memberId = memberIdCookie ? memberIdCookie.split('=')[1] : null;

    if (memberId) {
        router.push(`/member/${memberId}`);
    } else {
        alert('로그인이 필요합니다.');
        router.push('/login');
    }
}

function badge() {
    router.push("/badge/list");
}

</script>


<style scoped>
@import url('@/assets/css/main/Header.css');
</style>