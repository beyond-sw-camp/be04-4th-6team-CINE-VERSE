<template>
    <div>
      <button @click="likePost">좋아요</button>
    </div>
  </template>
  
  <script>
import axios from 'axios';

export default {
  data() {
    return {
      likeCount: 0,
      postId: null, // 게시글 ID
      memberId: null // 사용자 ID
    };
  },
  methods: {
    async fetchLikeCount() {
      try {
        const response = await axios.get(`http://localhost:8081/like/Info/${this.infoId}/${this.memberId}`);
      } catch (error) {
        console.error('좋아요 수를 불러오는 중 에러 발생:', error);
      }
    },
    async likePost() {
      try {
        await axios.get(`http://localhost:8081/like/Info/${this.infoId}/${this.memberId}`);
        this.fetchLikeCount(); // 좋아요를 클릭한 후에 좋아요 수를 다시 가져옵니다.
      } catch (error) {
        console.error('좋아요를 클릭하는 중 에러 발생:', error);
      }
    }
  },
  mounted() {
    // 페이지가 로드될 때 게시글 ID를 설정합니다. 여기서는 URL의 파라미터로부터 가져옵니다.
    this.infoId = this.$route.params.infoId;
    
    // 쿠키에서 사용자 ID를 가져와서 할당합니다.
    const memberIdCookie = document.cookie.split('; ').find(cookie => cookie.startsWith('memberId='));
    if (memberIdCookie) {
      this.memberId = memberIdCookie.split('=')[1];
    }

  }
};
</script>