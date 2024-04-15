<template>
    <div class="test">
        <div class="logincontent">
            <div class="login">
                <h1>로그인</h1>
            </div>
            <div class="choose1">
                <h5 class="choosing1" @click="login()">로그인</h5>
                <h5>|</h5>
                <h5 class="choosing2">ID 찾기</h5>
                <h5>|</h5>
                <h5 class="choosing3" @click="findPw()">PW 찾기</h5>
            </div>
            <div class="choose2">
                <!-- <hr class="basicline"> -->
                <!-- <hr class="colorline"> -->
            </div>
            <div class="maillabel">
                <p class="mailtext">E-mail</p>
                <input type="email" class="mailbox" id="mailbox" v-model.trim="member_Email"
                    placeholder="E-mail 주소를 입력해주세요.">
            </div>
            <div class="mailbtndiv">
                <button @click="findUserIdByEmail" class="mailbtn">아이디 찾기</button>
            </div>
            <div class="idlabel">
                <p class="idtext">아이디 확인</p>
                <button class="idbox">{{ foundUserId }}</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const member_Email = ref('');
const foundUserId = ref(null); // 찾은 아이디를 저장할 ref

const findUserIdByEmail = async () => {
    try {
        const response = await axios.post('http://localhost:8081/member/find_id', { memberEmail: member_Email.value });
        if (response.data === "존재하지 않는 이메일입니다.") {
            alert(response.data);
        } else {
            foundUserId.value = response.data; // 서버로부터 받은 아이디를 저장
        }
    } catch (error) {
        console.error('아이디를 찾는 중 에러가 발생했습니다.', error);
        alert('아이디 찾기에 실패했습니다.');
    }
};

function login() {
    router.push("/member/login");
}

function findPw() {
    router.push("/member/findaccountPw");
}

</script>

<style scoped>
@import url('@/assets/css/user/FindUserInfoId.css');
</style>