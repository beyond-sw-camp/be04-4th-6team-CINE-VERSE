<template>
    <div class="test">
        <div class="joincontent">
            <div class="join">
                <h1>회원가입</h1>
            </div>
            <div class="joinbox">
                <form @submit.prevent="submitForm">
                    <div class="idlabel">
                        <p class="idtext">ID</p>
                        <input type="text" class="idbox" id="idbox" v-model.trim="member_id" placeholder="아이디를 입력해주세요.">
                    </div>
                    <div class="pwlabel">
                        <p class="pwtext">Password</p>
                        <input type="password" class="pwbox" id="pwbox" v-model.trim="member_pwd"
                            placeholder="영어 대문자, 소문자, 숫자를 포함한 10자 이하로 입력해주세요."
                            pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{1,10}"
                            title="비밀번호는 영어 대문자, 소문자, 숫자를 포함한 10자 이하로 입력해주세요.">
                    </div>
                    <div class="pwlabel2">
                        <p class="pwtext2">Password Check</p>
                        <input type="password" class="pwbox2" id="pwbox2" v-model.trim="member_pwd_check"
                            placeholder="비밀번호를 한 번 더 입력해주세요.">
                    </div>
                    <div class="nicknamelabel">
                        <p class="nicknametext">Nickname</p>
                        <input type="text" class="nicknamebox" id="nicknamebox" v-model.trim="member_nickname"
                            placeholder="한글, 영문 대소문자, 숫자, 공백 포함 10글자 이하로 입력해주세요."
                            pattern="^[ㄱ-ㅎ가-힣a-zA-Z0-9\s]{1,10}$"
                            title="닉네임은 한글, 영문 대소문자, 숫자, 공백 포함 10글자 이하로 입력해주세요.">
                    </div>
                    <div class="maillabel">
                        <p class="mailtext">E-mail</p>
                        <input type="email" class="mailbox" id="mailbox" v-model.trim="member_email"
                            placeholder="이메일 주소를 입력해주세요.">
                    </div>
                    <div class="namelabel">
                        <p class="nametext">Name</p>
                        <input type="text" class="namebox" id="namebox" v-model.trim="member_name"
                            placeholder="성함을 입력해주세요.">
                    </div>
                    <div class="phonelabel">
                        <p class="phonetext">Phone</p>
                        <input type="tel" class="phonebox" id="phonebox" v-model.trim="member_phone"
                            placeholder="전화번호를 입력해주세요. 예시: 010-1234-5678, 02-1234-5678"
                            pattern="^[0-9]{2,3}-\d{3,4}-\d{4}$"
                            title="전화번호를 입력해주세요. 예시: 010-1234-5678, 02-1234-5678">
                    </div>
                    <div class="datelabel">
                        <p class="datetext">Birthdate</p>
                        <input type="date" class=datebox id="datebox" v-model.trim="member_date">
                    </div>
                    <div class="joinbtndiv">
                        <button type="submit" class="joinbtn">회원가입</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/mainRouter';

const member_id = ref('');
const member_pwd = ref('');
const member_pwd_check = ref('');
const member_nickname = ref('');
const member_email = ref('');
const member_name = ref('');
const member_phone = ref('');
const member_date = ref('');

const submitForm = async () => {
    if (!validateForm()) return;

    try {
        const response = await axios.post('http://localhost:8081/member/regist', {
            userId: member_id.value,
            userPassword: member_pwd.value,
            nickname: member_nickname.value,
            memberEmail: member_email.value,
            Nickname: member_name.value,
            memberNumber: member_phone.value,
            memberDate: member_date.value,
            memberGrade: 1
        }, {
            headers: { 'Content-Type': 'application/json' },
            withCredentials: true
        });

        if (response.status === 201) {
            alert('회원 가입이 완료되었습니다.');

            router.push('/member/login');
        }
    } catch (error) {
        alert('회원 가입에 실패하였습니다.');
        console.error(error);
    }
};

function validateForm() {
    if (!member_id.value.trim() || !member_pwd.value.trim() || !member_nickname.value.trim() ||
        !member_email.value.trim() || !member_name.value.trim() || !member_phone.value.trim() ||
        !member_date.value) {
        alert('모든 필드를 채워주세요.');
        return false;
    }

    if (member_pwd.value !== member_pwd_check.value) {
        alert('비밀번호를 재확인해주세요.');
        return false;
    }

    return true;
}

</script>

<style scoped>
@import url('@/assets/css/user/Join.css');
</style>