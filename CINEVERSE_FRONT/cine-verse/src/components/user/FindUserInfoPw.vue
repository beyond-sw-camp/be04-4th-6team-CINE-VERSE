<template>
    <div class="test">
        <div class="logincontent">
            <div class="login">
                <h1>로그인</h1>
            </div>
            <div class="choose1">
                <h5 class="choosing1" @click="login()">로그인</h5>
                <h5>|</h5>
                <h5 class="choosing2" @click="findId()">ID 찾기</h5>
                <h5>|</h5>
                <h5 class="choosing3">PW 찾기</h5>
            </div>
            <div class="choose2">
                <!-- <hr class="basicline"> -->
                <!-- <hr class="colorline"> -->
            </div>
            <div class="maillabel">
                <p class="mailtext">E-mail</p>
                <input type="email" id="mailbox" class="mailbox" v-model="memberEmail"
                    placeholder="회원 E-mail 주소를 입력 후 비밀번호 재설정 이메일을 받아주세요.">
            </div>
            <div class="mailbtndiv">
                <button class="mailbtn" @click="resetPassword">재설정 비밀번호 받기</button>
            </div>
            <!-- <div class="numlabel">
            <p class="numtext">재설정 비밀번호 확인</p>
            <input type="password" id="numbox" class="numbox" v-model.trim="re_pwd" placeholder="재설정된 비밀번호를 입력 후 회원 정보를 수정해주세요.">
        </div> -->
            <!-- <div class="numbtndiv1">
            <button class="numbtn1">인증번호 확인</button>
        </div> -->
            <!-- <div class="numbtndiv2">
            <button class="numbtn2" @click="modifyMemberInfo">회원정보 수정</button>
        </div> -->
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import axios from 'axios';

const router = useRouter();
const memberEmail = ref('');

async function resetPassword() {
    try {
        const response = await axios.post('http://localhost:8081/email/send', {
            to: memberEmail.value
        });

        if (response.status === 200) {
            alert('비밀번호 재설정 이메일을 성공적으로 보냈습니다.');
        } else {
            throw new Error('비밀번호 재설정 이메일을 재확인해주세요.');
        }
    } catch (error) {
        alert('비밀번호 재설정 이메일 전송에 실패했습니다. 오류: ' + error);
        console.error(error);
    }
}

function login() {
    router.push("/member/login");
}

function findId() {
    router.push("/member/find_id");
}

</script>

<style scoped>
@import url('@/assets/css/user/FindUserInfoPw.css');
</style>