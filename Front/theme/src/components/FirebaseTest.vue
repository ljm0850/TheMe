<template>
    <div>
        <div ref="notification-list" class="list" @scroll="handleNotificationListScroll">
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
        <div>더미 디브</div>
    </div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
    <div>끝</div>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
</div>
</template>

<script lang="ts">
// import { reactive } from '@vue/reactivity'
// import { useStore } from "vuex";
export default {
    components: {
    },
    setup() {
        const handleNotificationListScroll = (e:any) => {
            const { scrollHeight, scrollTop, clientHeight } = e.target;
            const isAtTheBottom = scrollHeight == scrollTop + clientHeight;
            // 일정 한도 밑으로 내려오면 함수 실행
            console.log("ㅇㅇㅇㅇ")
            if (isAtTheBottom) {
                console.log("z")
                handleLoadMore()
            }
        }

        const handleLoadMore = () => {
            console.log("도착")
        }

        return { handleNotificationListScroll }
    }
}
</script>

<style lang="scss">

</style>