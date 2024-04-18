import { createRouter, createWebHistory } from 'vue-router';

import MemberView from '@/components/View/Member.vue';
import EventView from '@/components/View/event.vue';
import ReviewView from '@/components/View/review.vue';
import InfoView from '@/components/View/info.vue';
import FreeView from '@/components/View/free.vue';
import MainView from '@/components/Main/Main.vue';
import UserProfile from '@/components/User/UserProfile.vue';
import Join from '@/components/User/Join.vue';
import EditUserInfo from '@/components/User/EditUserInfo.vue';
import Login from '@/components/User/Login.vue';
import FindUserInfoId from '@/components/User/FindUserInfoId.vue';
import FindUserInfoPw from '@/components/User/FindUserInfoPw.vue';
import Badge from '@/components/User/Badge.vue';
import MyBadge from '@/components/User/MyBadge.vue';
import EventPostMain from '@/components/PostMain/EventPostMain.vue';
import EventPost from '@/components/PostAndReply/EventPost.vue';
import EventPostContent from '@/components/CreatePost/eventPostContent.vue';
import InfoPostMain from '@/components/PostMain/PostMain.vue';
import InfoPost from '@/components/PostAndReply/InfoPost.vue';
import InfoPostContent from '@/components/CreatePost/InfoPostContent.vue';
import ReviewPostMain from '@/components/PostMain/ReviewPostMain.vue';
import ReviewPost from '@/components/PostAndReply/ReviewPost.vue';
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
