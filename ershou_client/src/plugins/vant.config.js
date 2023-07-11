module.exports = {
    theme: {
        extend: {
            //修改van-image在加载时的图片展示
            'van-image': {
                loading: 'logo.png',
                error: 'https://example.com/error.png',
            },
        },
    },
};
