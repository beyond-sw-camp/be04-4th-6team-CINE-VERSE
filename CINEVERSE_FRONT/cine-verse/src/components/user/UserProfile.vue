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
                <img :src="wearingBadge.badge.badgeLocation" class="mybadgeimage">
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
            <!-- <div class="userpostbox">
            <p class="userpost1">작성 게시글</p>
            <ul>
                <li v-for="(post, index) in userPosts" :key="index">
                    <h3>{{ post.freeTitle }}</h3> 
                    <p>{{ post.freeDate }}</p> 
                </li>
            </ul>
        </div> -->
            <!-- <div class="likepostbox">
            <p class="likepost1">좋아요한 게시글</p>
            <div class="likepost2">좋아요한 게시글 5개</div>
        </div> -->
            <!-- <div class="userreplybox">
            <p class="userreply1">작성 댓글</p>
            <div class="userreply2">작성 댓글 5개</div>
        </div> -->
        </div>
    </div>
</template>

<script setup>
import axios from "axios";
import { onMounted, ref, computed } from "vue";
import { useRoute, useRouter } from 'vue-router';

const memInfo = ref({});
const pointInfo = ref({});
const wearingBadge = ref(null);

const totalPoint = computed(() => pointInfo.value.totalPoint || 0);

const router = useRouter();
const route = useRoute();

// 회원 정보와 포인트 정보
async function fetchMemberData(memberId) {
    try {
        const memberResponse = await axios.get(`http://localhost:8081/member/${memberId}`);
        memInfo.value = memberResponse.data;
        console.log('회원 정보:', memInfo.value);

        const pointResponse = await axios.get(`http://localhost:8081/point/${memberId}`);
        pointInfo.value = pointResponse.data;
        console.log('포인트 정보:', pointInfo.value);
    } catch (error) {
        console.error('데이터를 가져오는데 실패했습니다', error);
    }
}

// 착용중인 뱃지 정보
async function fetchWearingBadge(memberId) {
    try {
        const response = await axios.get(`http://localhost:8081/badge/${memberId}`);
        wearingBadge.value = response.data.find(badge => badge.badgeStatus === 'Y');
        console.log('착용중인 뱃지 정보:', wearingBadge.value);
    } catch (error) {
        console.error('착용중인 뱃지 정보를 가져오는데 실패했습니다', error);
    }
}

onMounted(() => {
    const memberId = route.params.memberId || 1;
    fetchMemberData(memberId);         // 회원 정보와 포인트 정보
    fetchWearingBadge(memberId);       // 착용중인 뱃지 정보
});


function edit() {
    router.push("/member/modify/1");
}

function badge() {
    router.push("/badge/list");
}

function mybadge() {
    router.push("/badge/1");
}


async function deleteMember() {
    const confirmDelete = window.confirm("정말로 탈퇴하시겠습니까?");
    if (confirmDelete) {
        try {
            const memberId = memInfo.value.memberId;
            const userId = memInfo.value.userId;
            const nickname = memInfo.value.nickname;

            // console.log(`탈퇴한 회원 정보: memberId=${memberId}, userId=${userId}, nickname=${nickname}`); // userprofile의 memberId가 탈퇴

            await axios.patch('http://localhost:8081/member/delete/14');
            alert('회원 탈퇴가 완료되었습니다.');
            router.push('/main');
        } catch (error) {
            alert('회원 탈퇴 중 오류가 발생했습니다.');
            console.error(error);
        }
    }
}


// onMounted(async () => {
//     const memberId = route.params.memberId;

//     if (memberId && !isNaN(parseInt(memberId))) {
//         try {
//             const postsResponse = await axios.get(`http://localhost:8081/free_board/${memberId}`);
//             userPosts.value = postsResponse.data.slice(0, 5); // 처음 5개의 게시글만 저장
//         } catch (error) {
//             console.error('회원의 게시글 정보를 가져오는데 실패했습니다', error);
//         }
//     } else {
//         console.error('유효하지 않은 memberId입니다.');
//     }
// });
</script>

<style scoped>
@import url('@/assets/css/user/UserProfile.css');
</style>