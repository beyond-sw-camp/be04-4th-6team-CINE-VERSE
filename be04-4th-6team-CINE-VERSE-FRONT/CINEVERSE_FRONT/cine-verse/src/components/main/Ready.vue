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
    justify-content: center; 
    align-items: center;  
    height: 100vh;
    margin-left: 25%;
}

img.readyimage {
    max-width: 100%;
    max-height: 100%;
}
</style>
