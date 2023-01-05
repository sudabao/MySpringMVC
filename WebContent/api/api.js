
const apis = [
    {
        name: 'createUser',
        desc: '创建用户',
        params: {
            functionId: 'createUser',
            body: {
                nickName: '微信昵称',
                avatar: "https(微信头像地址)",
            }
        },
        response: {
            code: '0',
            subCode: '0',
            msg: "success",
            token: "5b2dd132-40df-4733-be99-3897942b6e8b"
        }
    },
    {
        name: 'indexAllContents',
        desc: '首页所有卡片列表',
        params: {
            functionId: 'indexAllContents',
            body: {
                token: "5b2dd132-40df-4733-be99-3897942b6e8b",
            }
        },
        response: {
            code: '0',
            subCode: '0',
            msg: "success",
            contentList: [
                {
                    author: {
                        name: 'dage',
                        avatar: 'http:***',
                        userId: "userId"
                    },
                    Tags: [
                        {
                            tagId: "tagId",
                            tagText: 'BE'
                        },
                        {
                            tagId: "tagId",
                            tagText: 'XX'
                        }
                    ],
                    mainContent: "正文正文",
                    createDate: '2019-10-10 12: 00: 00',
                    isCollected: "0（本人是否收藏了这个卡片，0-没有，1-收藏了）"
                }
            ]
        }
    },
    {
        name: 'createContent',
        desc: '创建推文|或者叫笔记也行，创建时间取服务器时间，精确到秒？',
        params: {
            functionId: 'createContent',
            body: {
                token: "5b2dd132-40df-4733-be99-3897942b6e8b",
                content: '正文正文正文（字数限制考虑一下）',
                tags: "BE_Tag1_Tag2_",
                title: "标题（可以为空）"
            }
        },
        response: {
            code: '0',
            subCode: '0',
            msg: "success",
            status: "创建状态（0 - 成功 1 -失败）"
        }
    },
    {
        name: 'userCreateList',
        desc: '个人主页 原创列表 (可能是自己的也可能是他人的，以token 和userId 对比来判断）',
        params: {
            functionId: 'userCreateList',
            body: {
                token: "5b2dd132-40df-4733-be99-3897942b6e8b",
                userId: '123 (自己的传‘’，去别人主页就传对方的userId）'
            }
        },
        response: {
            code: '0',
            subCode: '0',
            msg: "success",
            userInfo: {
                avatar: "https://ssss",
                nickName: '用户昵称'
            },
            contentList: [
                {
                    author: {
                        name: 'dage',
                        avatar: 'http:***',
                        userId: "userId"
                    },
                    Tags: [
                        {
                            tagId: "tagId",
                            tagText: 'BE'
                        },
                        {
                            tagId: "tagId",
                            tagText: 'XX'
                        }
                    ],
                    mainContent: "正文正文",
                    createDate: '2019-10-10 12: 00: 00',
                    isCollected: "0（本人是否收藏了这个卡片，0-没有，1-收藏了）"
                }
            ]
        }
    },
    {
        name: 'userCollectionList',
        desc: '个人主页 收藏列表 (可能是自己的也可能是他人的，以token 和userId 对比来判断）',
        params: {
            functionId: 'userCollectionList',
            body: {
                token: "自己维护的登录态令牌",
                userId: '123 (自己的传‘’，去别人主页就传对方的userId）'
            }
        },
        response: {
            code: '0',
            subCode: '0',
            msg: "success",
            userInfo: {
                avatar: "https://ssss",
                nickName: '用户昵称'
            },
            contentList: [
                {
                    author: {
                        name: 'dage',
                        avatar: 'http:***',
                        userId: "userId"
                    },
                    Tags: [
                        {
                            tagId: "tagId",
                            tagText: 'BE'
                        },
                        {
                            tagId: "tagId",
                            tagText: 'XX'
                        }
                    ],
                    mainContent: "正文正文",
                    createDate: '2019-10-10 12: 00: 00',
                    isCollected: "0（本人是否收藏了这个卡片，0-没有，1-收藏了）"
                }
            ]
        }
    },
    {
        name: 'collectContent',
        desc: '收藏|取消收藏 某一条推文，入参action判断是收藏还是取消',
        params: {
            functionId: 'collectContent',
            body: {
                token: "",
                action: "0-取消收藏，1-收藏",
                contentId: '123'
            }
        },
        response: {
            code: '0',
            subCode: '0',
            msg: "success",
            status: "收藏状态（0 - 成功 1 -失败）"
        }
    },
]