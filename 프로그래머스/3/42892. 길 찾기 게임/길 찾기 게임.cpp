#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
    int num, x, y;
    Node* left; Node* right;
};

bool compare(Node& a, Node& b) {
    if (a.y == b.y) return a.x < b.x;
    return a.y > b.y;
}

void insertNode(Node* parent, Node* child) {
    if (child->x < parent->x) {
        if (parent->left == nullptr)
            parent->left = child;
        else
            insertNode(parent->left, child);
    }
    else {
        if (parent->right == nullptr)
            parent->right = child;
        else
            insertNode(parent->right, child);
    }
}

void preorder(Node* node, vector<int>& result) {
    if (node == nullptr) return;
    result.push_back(node->num);
    preorder(node->left, result);
    preorder(node->right, result);
}

void postorder(Node* node, vector<int>& result) {
    if (node == nullptr) return;
    postorder(node->left, result);
    postorder(node->right, result);
    result.push_back(node->num);
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    vector<Node> nodes;
    for (int i = 0; i < nodeinfo.size(); i++) {
        nodes.push_back({ i + 1, nodeinfo[i][0], nodeinfo[i][1], nullptr, nullptr });
    }

   
    sort(nodes.begin(), nodes.end(), compare);

 
    Node* root = &nodes[0];
    for (int i = 1; i < nodes.size(); i++) {
        insertNode(root, &nodes[i]);
    }

 
    vector<int> preorderRes, postorderRes;
    preorder(root, preorderRes);
    postorder(root, postorderRes);

    return { preorderRes, postorderRes };
}