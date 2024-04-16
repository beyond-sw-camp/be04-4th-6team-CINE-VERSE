<template>
    <div class="test">
    <div class="logincontent">
        <div class="login">
            <h1>로그인</h1>
        </div>
        <div class="choose1">
            <h5 class="choosing1">로그인</h5>
            <h5>|</h5>
            <h5 class="choosing2" @click="findId()">ID 찾기</h5>
            <h5>|</h5>
            <h5 class="choosing3" @click="findPw()">PW 찾기</h5>
        </div>
        <div class="choose2">
            <!-- <hr class="basicline"> -->
            <!-- <hr class="colorline"> -->
        </div>
        <div class="loginbox">
            <form @submit.prevent="login">
                <div class="idlabel">
                    <p class="idtext">ID</p>
                    <input type="text" id="idbox" class="idbox" v-model="userId" placeholder="아이디를 입력해주세요.">
                </div>
                <div class="pwlabel">
                    <p class="pwtext">Password</p>
                    <input type="password" id="pwbox" class="pwbox" v-model="password" placeholder="비밀번호를 입력해주세요.">
                </div>
                <div class="loginbtndiv">
                    <button class="loginbtn">Log in</button>
                </div>
            </form>
        </div>
        <div class="joinbtndiv">
            <p class="question">계정이 없으신가요?</p>
            <button type="button" class="joinbtn" @click="join()">회원가입</button>
        </div>
    </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { inject, ref } from 'vue';
import { useRouter } from 'vue-router';

const userId = ref('');
const password = ref('');
const router = useRouter();
const isLoggedin = inject('isLoggedin');


const login = async () => {
    try {
        const response = await axios.post('http://localhost:8081/member/login', {
            userId: userId.value,
            userPassword: password.value
        },
        {
            withCredentials: true
        });

        console.log(response); // 서버 응답 전체 출력

        if (response.status === 200) {
            localStorage.setItem('sessionId', response);
            
            console.log("memberId:", response.data.memberId); // memberId 출력
            console.log("role:", response.data.role); // role 출력
            
            isLoggedin.value = true; // 로그인 상태 업데이트
            router.push('/'); // 상태 업데이트 후 페이지 이동

            alert('로그인 성공');

            router.push('/');
        }
    } catch (error) {
        console.log("로그인 실패:", error.response ? error.response.data.message : "Unknown Error")

        if (error.response && error.response.status === 401) {
            alert('로그인 실패: 아이디나 비밀번호를 확인해주세요.');
        } else {
            console.error('로그인 중 오류 발생:', error);
            alert('로그인 중 오류가 발생했습니다.');
        }
    }
};

function join() {
    router.push("/member/regist"); 
}

function findId() {
    router.push("/member/find_id"); 
}

function findPw() {
    router.push("/member/findaccountPw"); 
}

</script>


<style scoped>
@import url('@/assets/css/user/Login.css')
</style>