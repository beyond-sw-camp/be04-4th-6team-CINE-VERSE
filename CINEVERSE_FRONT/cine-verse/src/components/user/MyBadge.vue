<template>
    <div class="test">
        <div class="eventboardcontent">
            <div class="eventboard">
                <h1>내 뱃지</h1>
            </div>
            <div class="votecontent">
                <div class="votebtndiv">
                    <button class="votebtn">착용중</button>
                </div>
                <div class="voteboxesdiv" v-if="wearingBadge">
                    <div class="voteboxdiv1">
                        <div class="votebox1">
                            <img :src="wearingBadge.badge.badgeLocation" class="badgeimage">
                        </div>
                        <div class="votebox2">
                            {{ wearingBadge.badge.badgeId }}. {{ wearingBadge.badge.badgeName }}
                        </div>
                    </div>
                </div>
            </div>
            <div class="quizcontent">
                <div class="quizbtndiv">
                    <button class="quizbtn">구매한 뱃지</button>
                </div>
                <div class="quizboxesdiv">
                    <div class="quizboxdiv1" v-for="badge in purchasedBadges" :key="badge.badge.badgeId">
                        <div class="quizbox1">
                            <img :src="badge.badge.badgeLocation" class="badgeimage">
                        </div>
                        <div class="quizbox2">
                            {{ badge.badge.badgeId }}. {{ badge.badge.badgeName }}
                            <button class="like4" @click="wearBadge(badge)">착용하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>



<script setup>
import axios from "axios";
import { onMounted, ref} from "vue";

const wearingBadge = ref(null);
const purchasedBadges = ref([]);

const getMemberIdFromCookie = () => {
    const memberIdCookie = document.cookie.split('; ').find(row => row.startsWith('memberId='));
    return memberIdCookie ? memberIdCookie.split('=')[1] : null;
};

const wearBadge = async (badge) => {
    const memberId = getMemberIdFromCookie();
    if (!memberId) {
        console.error('로그인 여부를 확인해주세요.');
        return;
    }

    try {
        const memberBadgeDto = {
            badge: { badgeId: badge.badge.badgeId },
            member: { memberId }
        };
        await axios.post('http://localhost:8081/badge/use', memberBadgeDto);

        // 착용중인 뱃지 이동 -> 구매한 뱃지로
        if (wearingBadge.value) {
            purchasedBadges.value = [...purchasedBadges.value, wearingBadge.value]
                .map(b => ({ ...b, badgeStatus: 'N' })); // 모든 구매한 뱃지의 badgeStatus를 'N'으로 설정
        }

        // 새로운 뱃지 착용
        wearingBadge.value = badge;

        // 착용하지 않은 뱃지 목록에서 현재 착용한 뱃지 제거
        purchasedBadges.value = purchasedBadges.value
            .filter(b => b.badge.badgeId !== badge.badge.badgeId);
        
    } catch (error) {
        console.error("뱃지 착용 중 에러가 발생했습니다:", error);
    }
};

onMounted(async () => {
    const memberId = getMemberIdFromCookie();
    if (memberId) {
        try {
            const response = await axios.get(`http://localhost:8081/badge/${memberId}`);
            wearingBadge.value = response.data.find(badge => badge.badgeStatus === 'Y');
            purchasedBadges.value = response.data.filter(badge => badge.badgeStatus === 'N');
        } catch (error) {
            console.error("뱃지 데이터를 가져오는 중 에러가 발생했습니다: ", error);
        }
    } else {
        console.error("로그인 여부를 확인해주세요.");
    }
});
</script>




<style scoped>
@import url('@/assets/css/user/MyBadge.css');
</style>