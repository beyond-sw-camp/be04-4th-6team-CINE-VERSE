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
import { onMounted, ref, inject } from "vue";

const globalState = inject('globalState');

const wearingBadge = ref(null);
const purchasedBadges = ref([]);

const wearBadge = async (badge) => {
    try {
        const memberBadgeDto = {
            badge: { badgeId: badge.badge.badgeId },
            member: { memberId: 1 }  // 동적 ID
        };
        await axios.post('http://localhost:8081/badge/use', memberBadgeDto);

        wearingBadge.value = badge;
        purchasedBadges.value = purchasedBadges.value.filter(b => b !== badge);

    } catch (error) {
        console.error("뱃지 착용 중 에러가 발생했습니다:", error);
    }
};

onMounted(async () => {
    // if (globalState.isLoggedin) {
        try {
            const response = await axios.get('http://localhost:8081/badge/1');
            wearingBadge.value = response.data.find(badge => badge.badgeStatus === 'Y');
            purchasedBadges.value = response.data.filter(badge => badge.badgeStatus === 'N');
        } catch (error) {
            console.error("뱃지 데이터를 가져오는 중 에러가 발생했습니다: ", error);
        }
    // } else {
    //     console.log("사용자가 로그인하지 않았습니다.");
    // }
});
</script>



<style scoped>
.eventboardcontent {
    display: flex;
    flex-direction: column;
    width: 100%;
}

.eventboard,
.votecontent,
.quizcontent {
    width: 100%;
}

.eventboard {
    margin-bottom: 50px;
    margin-top: 50px;
}

.votebtndiv,
.quizbtndiv {
    display: flex;
    justify-content: flex-start;
    margin-bottom: 20px;
    padding-left: 10px;
}


.voteboxesdiv,
.quizboxesdiv {
    display: flex;
    justify-content: space-between;
    margin-bottom: 50px;
    margin-top: 10px;
}

.quizboxesdiv {
    margin-bottom: 100px;
}

.voteboxdiv1,
.voteboxdiv2,
.voteboxdiv3,
.quizboxdiv1,
.quizboxdiv2,
.quizboxdiv3 {
    display: flex;
    flex-direction: column;
    align-items: center;
    flex-basis: calc(33.333% - 20px);
    margin: 0 10px;
}

.votebtn,
.quizbtn {
    padding: 10px 20px;
    background-color: #f76c6c;
    border: none;
    border-radius: 5px;
    color: white;
    cursor: pointer;
    font-size: 1em;
}

.votebox1,
.votebox3,
.votebox5,
.quizbox1,
.quizbox3,
.quizbox5 {
    font-weight: bold;
    margin-bottom: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 250px;
    width: 100%;
    border: 2px solid #FF786C;
    border-radius: 5px;
}

.votebox2,
.votebox4,
.votebox6,
.quizbox2,
.quizbox4,
.quizbox6 {
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: #FFF1F0;
    padding: 20px;
    border-radius: 5px;
    width: 100%;
    box-sizing: border-box;
}

.like1,
.like2,
.like3,
.like4,
.like5,
.like6 {
    margin-left: 10px;
    padding: 5px 10px;
    background-color: white;
    border: 2px solid #FF786C;
    border-radius: 5px;
    color: #FF786C;
    cursor: pointer;
    font-weight: bold;
}

.like1:hover,
.like2:hover,
.like3:hover,
.like4:hover,
.like5:hover,
.like6:hover {
    background-color: #FF786C;
    color: white;
}

.liked {
    background-color: #FF786C;
    color: white;
}

.eventboardcontent,
.votecontent,
.quizcontent {
    max-width: 1000px;
    margin: 0 auto;
}

.test {
    margin-left: 300px;
}

.votecontent {
    min-width: 1000px;
}

.badgeimage {
    width: 200px;
    height: 200px;
    border-radius: 100%;
}
</style>