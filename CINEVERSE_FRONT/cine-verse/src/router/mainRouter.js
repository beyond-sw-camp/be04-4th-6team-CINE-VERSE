import {createRouter, createWebHistory} from 'vue-router';


const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/member/:memberId',
            component: () => import('@/components/user/UserProfile.vue')
        }
        ,
        {
            path: '/member/regist',
            component: () => import('@/components/user/Join.vue')       
        }
        ,
        {
            path: '/member/modify/:memberId',
            component: () => import('@/components/user/EditUserInfo.vue')
        }
        ,
        {
            path: '/member/login',
            component: () => import('@/components/user/Login.vue')
        }
        ,
        {
            path: '/',
            component: () => import('@/components/main/Main.vue')
        }
        ,
        {
            path: '/member/find_id',
            component: () => import('@/components/user/FindUserInfoId.vue')
        }
        ,
        {
            path: '/member/findaccountPw',
            component: () => import('@/components/user/FindUserInfoPw.vue')
        }
        ,
        {
            path: '/member/point',
            component: () => import('@/components/user/UserProfile.vue')
        }
        ,
        {
            path: '/badge/list',
            component: () => import('@/components/user/Badge.vue')
        }
        ,
        {
            path: '/badge/:memberId',
            component: () => import('@/components/user/MyBadge.vue')
        }
        ,
        {
            path: '/event_board/list',
            component: () => import('@/components/PostMain/eventPostMain.vue')
        }
        ,
        {
            path: '/info_board/:infoId',
            component: () => import('@/components/PostAndReply/infoPost.vue')
        }
        ,
        {
            path: '/info_board/list',
            component: () => import('@/components/PostMain/PostMain.vue')
        }
        ,
        {
            path: '/info_board/regist',
            component: () => import('@/components/CreatePost/InfoPostContent.vue')
        }
        ,
        {
            path: '/info_board/modify/:infoId',
            component: () => import('@/components/editPost/InfoPostEdit.vue')
        }
        ,
        {
            path: '/review_board/:reviewId',
            component: () => import('@/components/PostAndReply/reviewPost.vue')
        }
        ,
        {
            path: '/review_board/list',
            component: () => import('@/components/PostMain/reviewPostMain.vue')
        }
        ,
        {
            path: '/review_board/regist',
            component: () => import('@/components/CreatePost/InfoPostContent.vue')
        }
        ,
        {
            path: '/review_board/modify/:reviewId',
            component: () => import('@/components/editPost/InfoPostEdit.vue')
        }
        ,
        {
            path: '/free_board/:freeId',
            component: () => import('@/components/PostAndReply/freePost.vue')
        }
        ,
        {
            path: '/free_board/list',
            component: () => import('@/components/PostMain/freePostMain.vue')
        }
        ,
        {
            path: '/free_board/regist',
            component: () => import('@/components/CreatePost/InfoPostContent.vue')
        }
        ,
        {
            path: '/free_board/modify/:freeId',
            component: () => import('@/components/editPost/InfoPostEdit.vue')
        }

    ]
})


export default router;