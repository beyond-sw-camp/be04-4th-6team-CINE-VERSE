<template>
    <div class="test">
        <div class="joincontent">
            <div class="join">
                <h1>회원정보 수정</h1>
            </div>
            <div class="joinbox">
                <form @submit.prevent="submitForm">
                    <div class="idlabel">
                        <p class="idtext">ID</p>
                        <button class="idbox">{{ editUserInfo.userId }}</button>
                    </div>
                    <div class="pwlabel">
                        <p class="pwtext">Password</p>
                        <input type="password" class="pwbox" id="pwbox" v-model.trim="member_pwd"
                        placeholder="새 비밀번호를 입력해주세요.">
                    </div>
                    <div class="pwlabel2">
                        <p class="pwtext2">Password Check</p>
                        <input type="password" class="pwbox2" id="pwbox2" v-model.trim="member_pwd_check"
                            placeholder="비밀번호를 확인해주세요.">
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
                        <input type="email" class="mailbox" id="mailbox" v-model.trim="member_email" placeholder="이메일">
                    </div>
                    <div class="phonelabel">
                        <p class="phonetext">Phone</p>
                        <input type="tel" class="phonebox" id="phonebox" v-model.trim="member_number"
                            placeholder="전화번호를 입력해주세요. 예시: 010-1234-5678, 02-1234-5678"
                            pattern="^[0-9]{2,3}-\d{3,4}-\d{4}$"
                            title="전화번호를 입력해주세요. 예시: 010-1234-5678, 02-1234-5678">
                    </div>
                    <div class="namelabel">
                        <p class="nametext">Name</p>
                        <button class="namebox">{{ editUserInfo.memberName }}</button>
                    </div>
                    <div class="datelabel">
                        <p class="datetext">Birthdate</p>
                        <button class="datebox">{{ editUserInfo.memberDate }}</button>
                    </div>
                    <div class="joinbtndiv">
                        <button type="submit" class="joinbtn">회원정보 수정</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
import router from '@/router/mainRouter';

const member_pwd = ref('');
const member_pwd_check = ref('');
const member_nickname = ref('');
const member_email = ref('');
const member_number = ref('');

const editUserInfo = ref({});

const route = useRoute();
const memberId = route.params.memberId;

onMounted(async () => {
    try {
        const response = await axios.get(`http://localhost:8081/member/${memberId}`);
        editUserInfo.value = response.data;
    } catch (error) {
        console.error('해당 회원 정보는 존재하지 않습니다.', error);
    }
});

const validateForm = () => {
    if (!member_pwd.value.trim() || !member_nickname.value.trim() ||
        !member_email.value.trim() || !member_number.value) {
        alert('모든 필드를 채워주세요.');
        return false;
    }

    if (member_pwd.value !== member_pwd_check.value) {
        alert('입력한 비밀번호가 일치하지 않습니다.');
        return false;
    }

    return true;
};

const submitForm = async () => {
    if (!validateForm()) {
        return;
    }

    const updateData = {
        userId: editUserInfo.value.userId,
        userPassword: member_pwd.value,
        nickname: member_nickname.value,
        Nickname: editUserInfo.value.Nickname,
        memberNumber: member_number.value,
        gradeId: editUserInfo.value.gradeId,
        memberEmail: member_email.value,
        memberDate: editUserInfo.value.memberDate
    };

    try {
        const response = await axios.patch(`http://localhost:8081/member/modify/${memberId}`, updateData);

        if (response.status === 200) {
            alert('회원 정보가 성공적으로 수정되었습니다.');
            router.push(`/member/${memberId}`);
        } else {
            throw new Error('응답 상태 코드가 200이 아닙니다.');
        }
    } catch (error) {
        alert('회원 정보 수정에 실패했습니다. 오류: ' + error);
        console.error(error);
    }
};

</script>

<style scoped>
@import url('@/assets/css/user/EditUserInfo.css');
</style>