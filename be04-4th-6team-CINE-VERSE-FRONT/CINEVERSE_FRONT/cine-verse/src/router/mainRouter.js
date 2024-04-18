import { createRouter, createWebHistory } from 'vue-router';

import MemberView from '@/components/View/Member.vue';
import EventView from '@/components/View/event.vue';
import ReviewView from '@/components/View/review.vue';
import InfoView from '@/components/View/info.vue';
import FreeView from '@/components/View/free.vue';
import MainView from '@/components/main/Main.vue';
import UserProfile from '@/components/user/UserProfile.vue';
import Join from '@/components/user/Join.vue';
import EditUserInfo from '@/components/user/EditUserInfo.vue';
import Login from '@/components/user/Login.vue';
import FindUserInfoId from '@/components/user/FindUserInfoId.vue';
import FindUserInfoPw from '@/components/user/FindUserInfoPw.vue';
import Badge from '@/components/user/Badge.vue';
import MyBadge from '@/components/user/MyBadge.vue';
import EventPostMain from '@/components/postMain/EventPostMain.vue';
import EventPost from '@/components/postAndReply/EventPost.vue';
import EventPostContent from '@/components/createPost/eventPostContent.vue';
import InfoPostMain from '@/components/postMain/PostMain.vue';
import InfoPost from '@/components/postAndReply/InfoPost.vue';
import InfoPostContent from '@/components/createPost/InfoPostContent.vue';
import ReviewPostMain from '@/components/postMain/ReviewPostMain.vue';
import ReviewPost from '@/components/postAndReply/ReviewPost.vue';
import ReviewPostContent from '@/components/createPost/reviewPostContent.vue';
import FreePostMain from '@/components/postMain/FreePostMain.vue';
import FreePost from '@/components/postAndReply/freePost.vue';
import FreePostContent from '@/components/createPost/freePostContent.vue';
import Ready from '@/components/main/Ready.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: MainView
        },
        {
            path: '/member',
            component: MemberView,
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
                    path: 'point',
                    component: UserProfile
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
            component: EventView,
            children: [
                {
                    path: 'list',
                    component: EventPostMain
                },
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
            component: InfoView,
            children: [
                {
                    path: 'list',
                    component: InfoPostMain
                },
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
            component: ReviewView,
            children: [
                {
                    path: 'list',
                    component: ReviewPostMain
                },
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
            component: FreeView,
            children: [
                {
                    path: 'list',
                    component: FreePostMain
                },
                {
                    path: 'regist',
                    component: FreePostContent
                },
                {
                    path: 'modify/:freeId',
                    component: EditUserInfo
                },
                {
                    path: '/S:freeId',
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
