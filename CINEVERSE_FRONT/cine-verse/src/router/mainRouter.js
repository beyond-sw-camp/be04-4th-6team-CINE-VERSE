import { createRouter, createWebHistory } from 'vue-router';

import MainView from '@/components/Main/Main.vue';
import UserProfile from '@/components/user/UserProfile.vue';
import Join from '@/components/user/Join.vue';
import EditUserInfo from '@/components/user/EditUserInfo.vue';
import Login from '@/components/user/Login.vue';
import FindUserInfoId from '@/components/user/FindUserInfoId.vue';
import FindUserInfoPw from '@/components/user/FindUserInfoPw.vue';
import Badge from '@/components/user/Badge.vue';
import MyBadge from '@/components/user/MyBadge.vue';
import EventPostMain from '@/components/PostMain/EventPostMain.vue';
import EventPost from '@/components/PostAndReply/eventPost.vue';
import EventPostContent from '@/components/CreatePost/eventPostContent.vue';
import InfoPostMain from '@/components/PostMain/PostMain.vue';
import InfoPost from '@/components/PostAndReply/infoPost.vue';
import InfoPostContent from '@/components/CreatePost/InfoPostContent.vue';
import ReviewPostMain from '@/components/PostMain/ReviewPostMain.vue';
import ReviewPost from '@/components/PostAndReply/reviewPost.vue';
import ReviewPostContent from '@/components/CreatePost/reviewPostContent.vue';
import FreePostMain from '@/components/PostMain/FreePostMain.vue';
import FreePost from '@/components/PostAndReply/freePost.vue';
import FreePostContent from '@/components/CreatePost/freePostContent.vue';
import Ready from '@/components/Main/Ready.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: MainView
        },
        {
            path: '/member',
            component: UserProfile,
            children: [
                {
                    path: 'regist',
                    component: Join
                },
                {
                    path: 'modify/:memberId',
                    component: EditUserInfo
                },
                {
                    path: 'login',
                    component: Login
                },
                {
                    path: 'find_id',
                    component: FindUserInfoId
                },
                {
                    path: 'findaccountPw',
                    component: FindUserInfoPw
                },
                {
                    path: 'badge',
                    component: Badge
                },
                {
                    path: ':memberId/badge',
                    component: MyBadge
                }
            ]
        },
        {
            path: '/event_board',
            component: EventPostMain,
            children: [
                {
                    path: ':eventId',
                    component: EventPost
                },
                {
                    path: 'regist',
                    component: EventPostContent
                }
            ]
        },
        {
            path: '/info_board',
            component: InfoPostMain,
            children: [
                {
                    path: 'regist',
                    component: InfoPostContent
                },
                {
                    path: 'modify/:infoId',
                    component: EditUserInfo
                },
                {
                    path: ':infoId',
                    component: InfoPost
                }
            ]
        },
        {
            path: '/review_board',
            component: ReviewPostMain,
            children: [
                {
                    path: 'regist',
                    component: ReviewPostContent
                },
                {
                    path: 'modify/:reviewId',
                    component: EditUserInfo
                },
                {
                    path: ':reviewId',
                    component: ReviewPost
                }
            ]
        },
        {
            path: '/free_board',
            component: FreePostMain,
            children: [
                {
                    path: 'regist',
                    component: FreePostContent
                },
                {
                    path: 'modify/:freeId',
                    component: EditUserInfo
                },
                {
                    path: '/:freeId',
                    component: FreePost
                }
            ]
        },
        {
            path:'/ready',
            component: Ready
        }
    ]
});

export default router;
