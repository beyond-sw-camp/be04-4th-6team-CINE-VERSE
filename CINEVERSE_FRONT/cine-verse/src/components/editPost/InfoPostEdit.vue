<template>
  <div>
    <h2>게시물 수정</h2>
    <form @submit.prevent="submitUpdate">
      <label for="infoTitle">제목:</label>
      <input type="text" id="infoTitle" v-model="infoTitle" required>
      
      <label for="infoContent">내용:</label>
      <textarea id="infoContent" v-model="infoContent" required></textarea>

      <label for="infoCategory">카테고리:</label>
      <input type="text" id="infoCategory" v-model="infoCategory" required>

      <label for="images">사진 업로드:</label>
      <input type="file" id="images" multiple accept="image/*" @change="handleFileChange">

      <button type="submit">게시물 수정</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/mainRouter';
import { useRoute } from "vue-router";

const infoTitle = ref('');
const infoContent = ref('');
const infoCategory = ref('');
const memberId = ref('1'); // 회원 아이디 설정

const images = ref([]);

const infoId = useRoute().params.infoId;

// 게시물 정보 불러오기 함수 추가
const fetchInfo = async () => {
  try {
    const response = await axios.get(`http://localhost:8081/info_board/${infoId}`);
    const { infoTitle: title, infoContent: content, infoCategory: category } = response.data;
    infoTitle.value = title;
    infoContent.value = content;
    infoCategory.value = category;
  } catch (error) {
    console.error("Error fetching info:", error);
  }
};

// 페이지가 마운트될 때 게시물 정보를 불러오도록 설정
fetchInfo();

const handleFileChange = (event) => {
  images.value = Array.from(event.target.files);
};

const submitUpdate = async () => {
  try {
    const formData = new FormData();
    formData.append('infoBoard', JSON.stringify({
      infoTitle: infoTitle.value,
      infoContent: infoContent.value,
      infoCategory: infoCategory.value, // 카테고리 정보 추가
      member: { memberId: memberId.value }
    }));

    if (images.value.length > 0) {
      images.value.forEach(image => {
        formData.append('images', image);
      });
    }

    await axios.patch(`http://localhost:8081/info_board/modify/${infoId}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

    router.push(`/info_board/list`);
  } catch (error) {
    console.error('Error updating post:', error);
  }
};
</script>