<template>
    <div class="preparation-container">
        <img src="@/assets/img/ready.png" class="readyimage">
    </div>
</template>

<script setup>
    import router from '@/router/mainRouter';
</script>

<style scoped>
.preparation-container {
    display: flex;
    justify-content: center; /* 수평 중앙 정렬 */
    align-items: center;     /* 수직 중앙 정렬 */
    height: 100vh;           /* 부모 컨테이너의 높이를 화면 전체로 설정 */
    margin-left: 25%;
}

img.readyimage {
    max-width: 100%;  /* 이미지가 컨테이너보다 크지 않도록 설정 */
    max-height: 100%; /* 이미지가 화면 높이를 초과하지 않도록 설정 */
}
</style>
