<template>
    <div class="test">
        <div class="eventboardcontent">
            <div class="eventboard">
                <h1>뱃지 상점</h1>
            </div>
            <div class="votecontent">
                <div class="votebtndiv">
                    <button class="votebtn">Marvel Comics</button>
                </div>
                <div class="voteboxesdiv">
                    <!-- 아이언맨 -->
                    <div class="voteboxdiv1">
                        <div class="votebox1">
                            <img :src="findBadgeById(1).badgeLocation" class="badgeimage">
                        </div>
                        <div class="votebox2">{{ findBadgeById(1).badgeId }}. {{ findBadgeById(1).badgeName }}
                            <button class="like1" @click="buyBadge(1)" :disabled="isPurchased(1)"
                                :class="{ purchased: isPurchased(1) }">
                                {{ purchaseButtonText(1) }}
                            </button>
                        </div>
                    </div>
                    <!-- 스파이더맨 -->
                    <div class="voteboxdiv2">
                        <div class="votebox3">
                            <img :src="findBadgeById(2).badgeLocation" class="badgeimage">
                        </div>
                        <div class="votebox4">{{ findBadgeById(2).badgeId }}. {{ findBadgeById(2).badgeName }}
                            <button class="like2" @click="buyBadge(2)" :disabled="isPurchased(2)"
                                :class="{ purchased: isPurchased(2) }">
                                {{ purchaseButtonText(2) }}
                            </button>
                        </div>
                    </div>
                    <!-- 토르 -->
                    <div class="voteboxdiv3">
                        <div class="votebox5">
                            <img :src="findBadgeById(3).badgeLocation" class="badgeimage">
                        </div>
                        <div class="votebox6">{{ findBadgeById(3).badgeId }}. {{ findBadgeById(3).badgeName }}
                            <button class="like3" @click="buyBadge(3)" :disabled="isPurchased(3)"
                                :class="{ purchased: isPurchased(3) }">
                                {{ purchaseButtonText(3) }}
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="quizcontent">
                <div class="quizbtndiv">
                    <button class="quizbtn">DC Comics</button>
                </div>
                <div class="quizboxesdiv">
                    <!-- 배트맨 -->
                    <div class="quizboxdiv1">
                        <div class="quizbox1">
                            <img :src="findBadgeById(4).badgeLocation" class="badgeimage">
                        </div>
                        <div class="quizbox2">{{ findBadgeById(4).badgeId }}. {{ findBadgeById(4).badgeName }}
                            <button class="like4" @click="buyBadge(4)" :disabled="isPurchased(4)"
                                :class="{ purchased: isPurchased(4) }">
                                {{ purchaseButtonText(4) }}
                            </button>
                        </div>
                    </div>
                    <!-- 슈퍼맨 -->
                    <div class="quizboxdiv2">
                        <div class="quizbox3">
                            <img :src="findBadgeById(5).badgeLocation" class="badgeimage">
                        </div>
                        <div class="quizbox4">{{ findBadgeById(5).badgeId }}. {{ findBadgeById(5).badgeName }}
                            <button class="like5" @click="buyBadge(5)" :disabled="isPurchased(5)"
                                :class="{ purchased: isPurchased(5) }">
                                {{ purchaseButtonText(5) }}
                            </button>
                        </div>
                    </div>
                    <!-- 조커 -->
                    <div class="quizboxdiv3">
                        <div class="quizbox5">
                            <img :src="findBadgeById(6).badgeLocation" class="badgeimage">
                        </div>
                        <div class="quizbox6">{{ findBadgeById(6).badgeId }}. {{ findBadgeById(6).badgeName }}
                            <button class="like6" @click="buyBadge(6)" :disabled="isPurchased(6)"
                                :class="{ purchased: isPurchased(6) }">
                                {{ purchaseButtonText(6) }}
                            </button>
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

const findBadgeById = (id) => {
    return badges.value.find(badge => badge.badgeId === id) || { badgeId: 'Not Found', badgeName: 'Unknown', badgeLocation: 'default.jpg' };
};

const isPurchased = (badgeId) => {
    return purchasedStatus.value[badgeId] || false;
};

const purchaseButtonText = (badgeId) => {
    return isPurchased(badgeId) ? "구매완료" : "$1000 구매하기";
};

// 뱃지 정보와 구매 여부
const badges = ref([]);
const purchasedStatus = ref({});
const isLoggedin = inject('isLoggedin');

const getMemberIdFromCookie = () => {
    const memberIdCookie = document.cookie.split('; ').find(row => row.startsWith('memberId='));
    return memberIdCookie ? memberIdCookie.split('=')[1] : null;
};

// 뱃지 정보
const fetchBadges = async () => {
    try {
        const response = await axios.get('http://localhost:8081/badge/list');
        badges.value = response.data;
    } catch (error) {
        console.error("뱃지 정보를 가져오는 중 에러가 발생했습니다:", error);
    }
};

// 뱃지 구매 여부
const fetchPurchaseStatus = async (memberId) => {
    try {
        const response = await axios.get(`http://localhost:8081/badge/${memberId}`);
        response.data.forEach(memberBadge => {
            purchasedStatus.value[memberBadge.badge.badgeId] =
                memberBadge.badgeStatus === 'Y' || memberBadge.badgeStatus === 'N';
        });
    } catch (error) {
        console.error("구매 상태를 가져오는 중 에러가 발생했습니다:", error);
    }
};

onMounted(async () => {
    await fetchBadges();  // 뱃지 정보는 항상

    if (isLoggedin.value) {  // 로그인 상태일 때만 구매 여부
        const memberId = getMemberIdFromCookie();
        if (memberId) {
            await fetchPurchaseStatus(memberId);
        }
    }
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

.purchased {
    background-color: #FF786C;
    color: white;
}

.purchased:hover {
    background-color: #FF786C;
}

.purchased::before {
    content: "\2665";
    margin-right: 5px;
}
</style>