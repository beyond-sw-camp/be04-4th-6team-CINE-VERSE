<template>
<button id="open-chatbot" @click="toggleModal"></button>
  <div v-if="isModalOpen" class="modal">
    <div id="chat-container" class="chat-container">
  <div id="chat-container" class="chat-container">
    <div id="chat-messages" class="chat-message">
      <div class="message" v-for="(message, index) in messages" :key="index">
        {{ message.sender }}: {{ message.content }}
      </div>
    </div>
  </div>
  </div>
    <div id="user-input">
      <input type="text" v-model="userMessage" placeholder="CINEVERSE에게 물어보세요!" @keydown.enter.prevent="sendMessage"/>
      <button @click="sendMessage">전송</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const isModalOpen = ref(false);

function toggleModal() {
  isModalOpen.value = !isModalOpen.value;
}

const apiKey = import.meta.env.VITE_OPENAI_API_KEY;
const apiEndpoint = 'https://api.openai.com/v1/chat/completions';

const messages = ref([]);
const userMessage = ref('');
  
async function fetchAIResponse(prompt) {

  const requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${apiKey}`
    },
    body: JSON.stringify({
      model: "gpt-3.5-turbo",
      messages: [{
        role: "user",
        content: prompt
      }],
      temperature: 0.8,
      max_tokens: 1024,
      top_p: 1,
      frequency_penalty: 0.5,
      presence_penalty: 0.5
    }),
  };

  try {
    const response = await fetch(apiEndpoint, requestOptions);
    const data = await response.json();
    return data.choices && data.choices.length > 0 ? data.choices[0].message.content : 'No response from AI';
  } catch (error) {
    console.error('OpenAI API 호출 중 오류 발생:', error);
    return 'OpenAI API 호출 중 오류 발생';
  }
}

async function sendMessage() {
  const message = userMessage.value.trim();
  if (message.length === 0) return;

  messages.value.unshift({ sender: '🗣', content: message });
  userMessage.value = '';

  const aiResponse = await fetchAIResponse(message);
  messages.value.unshift({ sender: '🎥', content: aiResponse });
}
</script>

<style scoped>
@import url('@/assets/css/chatbot/Chatbot.css');
</style>