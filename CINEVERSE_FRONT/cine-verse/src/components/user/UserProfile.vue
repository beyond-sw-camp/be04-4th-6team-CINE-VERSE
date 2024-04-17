<template>
    <div class="test">
        <div class="profilecontent">
            <div class="profile">
                <h1>회원 프로필</h1>
            </div>
            <div class="Profileimg">
                <img src="@/assets/img/spidermanprofile.jpg" class="profileimage">
            </div>
            <div class="nicknamediv">
                <button class="nickname">{{ memInfo.nickname }}</button>
            </div>
            <div class="userboxdiv">
                <div class="userbox1">
                    <div class="user1">회원 등급</div>
                    <div class="user2">{{ memInfo.gradeId }}</div>
                </div>
                <div class="userbox2">
                    <div class="user3">회원 이메일</div>
                    <div class="user4">{{ memInfo.memberEmail }}</div>
                </div>
                <div class="userbox3">
                    <div class="user5">가입 일자</div>
                    <div class="user6">{{ memInfo.joinDate }}</div>
                </div>
                <div class="userbox4">
                    <div class="user7">Point</div>
                    <div class="user8">{{ totalPoint }}</div>
                </div>
            </div>
            <div class="joinbtndiv">
                <button type="submit" class="joinbtn" @click="edit()">회원정보 수정</button>
            </div>
            <div class="myBadgeimg" v-if="wearingBadge">
                <img :src="wearingBadge.badge.badgeLocation" class="mybadgeimage" @click="mybadge">
            </div>
            <div class="mybadgebtndiv">
                <button type="submit" class="badge" @click="mybadge">내 뱃지</button>
            </div>
            <div class="Badgeimg">
                <img src="@/assets/img/badgebasket.png" class="badgeimage" @click="badge">
            </div>
            <div class="badgebtndiv">
                <button type="submit" class="badgebtn" @click="badge">뱃지 상점</button>
            </div>
            <div class="deletebtndiv">
                <button type="submit" class="deletebtn" @click="deleteMember">탈퇴하기</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref, computed, inject } from "vue";
import axios from "axios";
import { useRouter } from 'vue-router';

const memInfo = ref({});
const pointInfo = ref({});
const wearingBadge = ref(null);

const totalPoint = computed(() => pointInfo.value.totalPoint || 0);

const router = useRouter();
const isLoggedin = inject('isLoggedin');

async function fetchMemberData(memberId) {
    try {
        const memberResponse = await axios.get(`http://localhost:8081/member/${memberId}`);
        memInfo.value = memberResponse.data;
        console.log(memInfo.value);

        const pointResponse = await axios.get(`http://localhost:8081/point/${memberId}`);
        pointInfo.value = pointResponse.data[0];
        console.log(pointInfo.value);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

async function fetchWearingBadge(memberId) {
    try {
        const response = await axios.get(`http://localhost:8081/badge/${memberId}`);
        wearingBadge.value = response.data.find(b => b.badgeStatus === 'Y');
        console.log(wearingBadge.value);
    } catch (error) {
        console.error('Error fetching badge data:', error);
    }
}

onMounted(() => {
    const memberIdCookie = document.cookie
        .split('; ')
        .find(row => row.startsWith('memberId='));
    const memberId = memberIdCookie ? memberIdCookie.split('=')[1] : null;

    if (memberId) {
        fetchMemberData(memberId);
        fetchWearingBadge(memberId);
    } else {
        console.error('쿠키에서 memberId를 찾을 수 없습니다.');
    }
});

function edit() {
    const memberIdCookie = document.cookie
        .split('; ')
        .find(row => row.startsWith('memberId='));
    const memberId = memberIdCookie ? memberIdCookie.split('=')[1] : null;

    if (memberId) {
        router.push(`/member/modify/${memberId}`);
    } else {
        alert('로그인이 필요합니다.');
        router.push('/login');
    }
}

function badge() {
    router.push("/badge/list");
}

function mybadge() {
    const memberIdCookie = document.cookie
        .split('; ')
        .find(row => row.startsWith('memberId='));
    const memberId = memberIdCookie ? memberIdCookie.split('=')[1] : null;

    if (memberId) {
        router.push(`/badge/${memberId}`);
    } else {
        alert('로그인이 필요합니다.');
        router.push('/login');
    }
}

onMounted(() => {
    isLoggedin.value = !!localStorage.getItem('sessionId'); 
});

async function deleteMember() {
    const confirmDelete = window.confirm("정말로 탈퇴하시겠습니까?");
    if (confirmDelete) {
        try {
            const memberId = memInfo.value.memberId;
            const userId = memInfo.value.userId;
            const nickname = memInfo.value.nickname;

            console.log(`탈퇴한 회원 정보: memberId=${memberId}, userId=${userId}, nickname=${nickname}`); // userprofile의 memberId가 탈퇴

            await axios.patch(`http://localhost:8081/member/delete/${memberId}`);
            alert('회원 탈퇴가 완료되었습니다.');
            localStorage.removeItem('sessionId');
            router.push('/main');
            isLoggedin.value = false;
        } catch (error) {
            alert('회원 탈퇴 중 오류가 발생했습니다.');
            console.error(error);
        }
    }
}

</script>

<style scoped>
@import url('@/assets/css/user/UserProfile.css');
</style>