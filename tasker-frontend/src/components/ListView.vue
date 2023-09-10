<template>
  <div class="bg-gray-50">
    <div class="flex items-center justify-center h-screen">
      <div class="w-3/4 md:w-2/4">
        <form @submit.prevent="submitForm" class="w-full">
          <div class="flex items-center rounded-lg overflow-hidden shadow-md bg-white shadow-gray-200">
            <div class="flex-1">
              <input type="text" class="w-full bg-transparent p-3 outline-none px-4 text-gray-700 text-sm" placeholder="Enter a Task" v-model="task">
            </div>
            <div>
              <button class="p-3 px-7 bg-green-400 hover:bg-green-500 transition-all rounded-md text-white font-semibold text-sm shadow-xl" type="submit">
                {{ btnText }}
              </button>
            </div>
          </div>
        </form>

        <div class="mt-6 shadow-md shadow-gray-200 rounded-md max-h-80 overflow-y-auto bg-white" v-if="list.length > 0">
          <ul v-for="(item, index) in list" v-bind:key="index">
            <li class="flex items-center justify-between gap-4 text-sm text-gray-700 hover:bg-gray-50 p-3" :class="{ 'border-b border-gray-100': index !== list.length - 1 }">
              <div class="text-start font-semibold">{{ index + 1 }}</div>
              <div class="text-left flex-1">
                {{ item.task }}
              </div>
              <div class="text-left flex items-center gap-2">
                <button class="bg-blue-200 text-blue-600 p-1 rounded hover:bg-blue-500 hover:text-white transition-all" @click="EditItem(item.id)">
                  <span>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
                      <path stroke-linecap="round" stroke-linejoin="round"
                        d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10" />
                    </svg>


                  </span>
                </button>
                <button class="p-1 rounded bg-red-200 text-red-600 hover:bg-red-500 hover:text-white transition-all" @click="DeleteItem(item.id)">
                  <span>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                    </svg>

                  </span>
                </button>
              </div>
            </li>
          </ul>
        </div>


      </div>
    </div>
  </div>
</template>

<script setup>

import { ref, onMounted } from 'vue';

const task = ref('');
const btnText = ref('Add');
const list = ref([]);
const updateItemId = ref('');

onMounted(() => {
  fetch('http://localhost:3000/api/', {
    method: 'GET'
  })
    .then(res => res.json())
    .then(data => {
      list.value = data;
    })
});

const submitForm = () => {
  if (task.value.trim() === '') {
    alert("Please enter a task");
    return;
  }

  if (btnText.value == 'Update') {
    UpdateItem();
  } else {
    SaveItem();
  }
}

const DeleteItem = (id) => {
  fetch(`http://localhost:3000/api/delete/${id}`, {
    method: 'DELETE'
  }).then(res => res.json())
    .then(data => {
      list.value = data;
    })
}

const EditItem = (id) => {
  btnText.value = 'Update';
  task.value = list.value.find(item => item.id == id).task;
  updateItemId.value = id;
}

const UpdateItem = () => {
  fetch(`http://localhost:3000/api/update/${updateItemId.value}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      task: task.value
    })
  }).then(res => res.json())
    .then(data => {
      list.value = data;
    })
}

const SaveItem = () => {
  fetch('http://localhost:3000/api/save', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      task: task.value
    })
  }).then(res => res.json())
    .then(data => {
      list.value = data;
      task.value = '';
    })
}

</script>